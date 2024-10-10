package com.example;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.sql.SQLException;
import java.util.Scanner;

import com.example.controllers.Handler;
import com.sun.net.httpserver.HttpServer;

public class SimpleHttpApi {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        int port = 8080;
        int typeUser = 0;

        int optionAdmin = 0;
        int optionUser = 0;
        boolean exit = false;
        String exitOption;

        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

        Conexion.iniciarServidor();

        try {
            CrearTabla.usuarios();
        } catch (SQLException error) {
            error.printStackTrace();
        }

        server.setExecutor(null);
        server.start();

        System.out.println("Servidor online! Escuchando en el PUERTO: " + port);
        System.out.println();

        do {
            System.out.println("Selección de usuarios: 1. Usuario 2. Admin");
            typeUser = scanner.nextInt();

            scanner.nextLine();

            do {
                if (typeUser == 1) {
                    optionUser = App.menuUsuario(); // Recibe la opcíon del usuario elegida
                } else if (typeUser == 2) {
                    optionAdmin = App.menuAdmin(); // Recibe la opcíon del admin elegida
                } else {
                    System.out.println("Introduzca el tipo de usuario correcto");
                }
            } while (typeUser != 1 && typeUser != 2);

            if (optionUser == 1 || optionAdmin == 1) {
                // ENDPOINT: Crear solicitudes
                server.createContext("/api/solicitudes", new Handler());
            } else if (optionUser == 2 || optionAdmin == 2) {
                // ENDPOINT: Recibir solicitudes
                server.createContext("/api/solicitudes", new Handler());
            } else if (optionUser == 3 || optionAdmin == 3) {
                // ENDPOINT: Editar solicitud
                server.createContext("/api/solicitudes/{id}", new Handler());
            } else if (optionAdmin == 4) {
                // ENDPOINT: Recibir solicitudes por FECHA (ASC)
                server.createContext("/api/solicitudes/orden", new Handler());
            } else if (optionAdmin == 5) {
                // ENDPOINT: Saber si una solicitud está en curso (TRUE-FALSE)
                server.createContext("/api/solicitudes/comprobar/{id}", new Handler());
            } else if (optionAdmin == 6) {
                // ENDPOINT: Marcar solicitud como pendiente
                server.createContext("/api/solicitudes/pendiente/{id}", new Handler());
            } else if (optionAdmin == 7) {
                // ENDPOINT: Marcar solicitud como en curso
                server.createContext("/api/solicitudes/en-curso/{id}", new Handler());
            } else if (optionAdmin == 8) {
                // ENDPOINT: Marcar solicitud como finalizada
                server.createContext("/api/solicitudes/finalizada/{id}", new Handler());
            } else if (optionAdmin == 9) {
                // ENDPOINT: Eliminar solicitud
                server.createContext("/api/solicitudes/{id}", new Handler());
            }

            System.out.println();
            System.out.println(
                    "¿Desea salir o volver a realizar una tarea? Introduzca SI para salir o NO para seguir haciendo consultas");
            exitOption = scanner.nextLine().trim().toUpperCase();

            switch (exitOption) {
                case "SI":
                    exit = true;
                    break;

                case "NO":
                    exit = false;
                    break;

                default:
                    System.out.println("Introduzca una opción válida");
                    break;
            }
        } while (!exit);

        System.out.println();
        System.out.println("¡Hasta la próxima!");

        scanner.close();
    }
}