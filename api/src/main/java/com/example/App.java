package com.example;

import java.util.Scanner;

public class App {
    public static int menuAdmin() {
        String input;
        System.out.println("Seleccionar opcion");
        System.out.println("1. Crear nueva solicitud");
        System.out.println("2. Ver todas las solicitudes");
        System.out.println("3. Modificar una solicitud");
        System.out.println("4. Ver todas las solicitudes ordenado por fecha");
        System.out.println("5. Ver si una solicitud esta en curso");
        System.out.println("6. Marcar solicitud como pendiente");
        System.out.println("7. Marcar solicitud como en curso");
        System.out.println("8. Marcar solicitud como finalizada");
        System.out.println("9. Eliminar solicitud finalizada");
        input = new Scanner(System.in).next();
        switch (input) {
            case "1":
                return 1;
            case "2":
                return 2;
            case "3":
                return 3;
            case "4":
                return 4;
            case "5":
                return 5;
            case "6":
                return 6;
            case "7":
                return 7;
            case "8":
                return 8;
            case "9":
                return 9;
            default:
                System.out.println("Input no valido");
                return 0;
        }
    }

    public static int menuUsuario() {
        String input;
        System.out.println();
        System.out.println("Seleccionar opcion");
        System.out.println("1. Crear nueva solicitud");
        System.out.println("2. Ver todas las solicitudes");
        System.out.println("3. Modificar una solicitud");
        input = new Scanner(System.in).next();
        switch (input) {
            case "1":
                return 1;
            case "2":
                return 2;
            case "3":
                return 3;
            default:
                System.out.println("Input no valido");
                return 0;
        }
    }
}
