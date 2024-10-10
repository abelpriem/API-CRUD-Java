package com.example;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Scanner;

import com.example.model.Solicitudes;

public class SimpleHttpApi {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        int port = 8080;
        int typeUser = 0;

        int optionSelected = 0;
        boolean exit = false;
        String exitOption;

        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

        Conexion.iniciarServidor();

        try {
            CrearTabla.usuarios();
            CrearTabla.solicitudes();
        } catch (SQLException error) {
            error.printStackTrace();
        }

        server.setExecutor(null);
        server.start();

        System.out.println("Servidor online! Escuchando en el PUERTO: " + port);

        do {
            System.out.println();
            System.out.println("Selección de usuarios: 1. Usuario 2. Admin");
            typeUser = scanner.nextInt();

            scanner.nextLine();

            do {
                if (typeUser == 1) {
                    optionSelected = App.menuUsuario();
                } else if (typeUser == 2) {
                    optionSelected = App.menuAdmin();
                } else {
                    System.out.println("Introduzca el tipo de usuario correcto");
                }
            } while (typeUser != 1 && typeUser != 2);

            if (optionSelected == 1) {
                String nombreUsuario;
                String temaSolicitud;
                String descripcionSolicitud;

                System.out.println();
                System.out.println("Introduzca el nombre de usuario: ");
                nombreUsuario = scanner.nextLine();
                System.out.println();

                System.out.println("Introduzca el tema de la solicitud: ");
                temaSolicitud = scanner.nextLine();
                System.out.println();

                System.out.println("Introduzca la descripción de la solicitud: ");
                descripcionSolicitud = scanner.nextLine();
                System.out.println();

                SolicitudesDAO.crear(nombreUsuario, temaSolicitud, descripcionSolicitud);

                optionSelected = 0;
            } else if (optionSelected == 2) {
                Solicitudes[] lista = SolicitudesDAO.obtenerLista();

                if (lista.length == 0) {
                    System.out.println();
                    System.out.println("No hay solicitudes creadas todavía.. Inténtelo de nuevo");
                } else {
                    System.out.println(Arrays.toString(lista));
                }

                optionSelected = 0;
            } else if (optionSelected == 3) {
                int id = 0;
                String editarTema;
                String editarDescripcion;

                System.out.println();
                System.out.println("Introduzca el ID de la solicitud: ");
                id = scanner.nextInt();
                System.out.println();

                scanner.nextLine();

                System.out.println("Introduzca la modificación del tema: ");
                editarTema = scanner.nextLine();
                System.out.println();

                System.out.println("Introduzca la modificación de la descripción: ");
                editarDescripcion = scanner.nextLine();
                System.out.println();

                SolicitudesDAO.actualizar(id, editarTema, editarDescripcion);

                optionSelected = 0;
            } else if (optionSelected == 4) {
                Solicitudes[] listaPorFecha = SolicitudesDAO.obtenerListaOrdenadoPorFecha();
                System.out.println(Arrays.toString(listaPorFecha));

                optionSelected = 0;
            } else if (optionSelected == 5) {
                int id = 0;

                System.out.println();
                System.out.println("Introduce el ID a consultar: ");
                id = scanner.nextInt();

                scanner.nextLine();

                SolicitudesDAO.estaEnCurso(id);

                optionSelected = 0;
            } else if (optionSelected == 6) {
                int id = 0;

                System.out.println();
                System.out.println("Introduce el ID a marcar status como PENDIENTE: ");
                id = scanner.nextInt();

                scanner.nextLine();

                SolicitudesDAO.marcarPendiente(id);

                System.out.println();

                optionSelected = 0;
            } else if (optionSelected == 7) {
                int id = 0;

                System.out.println();
                System.out.println("Introduce el ID a marcar status como EN CURSO: ");
                id = scanner.nextInt();

                scanner.nextLine();

                SolicitudesDAO.marcarEnCurso(id);

                optionSelected = 0;
            } else if (optionSelected == 8) {
                int id = 0;

                System.out.println();
                System.out.println("Introduce el ID a marcar status como FINALIZADA: ");
                id = scanner.nextInt();

                scanner.nextLine();

                SolicitudesDAO.marcarFinalizada(id);

                optionSelected = 0;
            } else if (optionSelected == 9) {
                int id = 0;

                System.out.println();
                System.out.println("Introduce el ID de la solicitud a ELIMINAR: ");
                id = scanner.nextInt();

                scanner.nextLine();

                SolicitudesDAO.eliminar(id);

                optionSelected = 0;
            }

            System.out.println();
            System.out.println(
                    "¿Desea salir o volver a realizar una tarea? Introduzca NO para salir o SI para seguir haciendo consultas");
            exitOption = scanner.nextLine().trim().toUpperCase();

            switch (exitOption) {
                case "NO":
                    exit = true;
                    break;

                case "SI":
                    exit = false;
                    break;

                default:
                    System.out.println("Introduzca una opción válida");
                    break;
            }
        } while (!exit);

        System.out.println("Cerrando servidor...");
        server.stop(0);

        System.out.println();
        System.out.println("¡Hasta la próxima!");

        scanner.close();
    }
}