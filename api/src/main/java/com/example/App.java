package com.example;

import java.sql.SQLException;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
/*

    static String input;
    public static void main( String[] args )
    {   
        Object[]lista;
        String input;
        boolean exit=false;
        int rol;
        Conexion.iniciarServidor();
        try{
        CrearTabla.usuarios();
        CrearTabla.solicitudes();
        }catch(SQLException e){
            e.printStackTrace();
        }
        System.out.println("Introducir rol");
        System.out.println("1. Usuario | 2. Admin");
        rol = new Scanner(System.in).nextInt();
        if(rol == 1){
            menuUsuario();
        }else if(rol == 2){
            menuAdmin();
        }else{
            System.out.println("Input no valido");
        }

        while(!exit){
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

            input=new Scanner(System.in).next();
            switch (input) {
                case "1":
                    SolicitudesDAO.crear("Abel", "Problema de acceso a la aplicación", "Solicito asistencia para resolver un problema de acceso a la aplicación. No puedo iniciar sesión con mis credenciales, y he intentado restablecer la contraseña sin éxito. Agradezco cualquier ayuda para solucionar este inconveniente lo antes posible.");
                    SolicitudesDAO.crear("Maria", "Error en el sistema", "He encontrado un error en el sistema al intentar guardar mis datos. Aparece un mensaje de error que dice 'no se puede guardar el registro'. Solicito ayuda para resolverlo.");
                    SolicitudesDAO.crear("Oscar", "Solicitud de nueva funcionalidad", "Me gustaría proponer una nueva funcionalidad para la aplicación que permita a los usuarios personalizar su perfil. Creo que esto mejoraría la experiencia del usuario.");
                    break;
                case "2":
                lista=SolicitudesDAO.obtenerLista();
                if(lista.length==0){
                    System.out.println("No hay solicitudes que mostrar");
                }else{
                    for(Object o:lista){
                        System.out.println(((Solicitudes)o).toString());
                    }
                }
                break;
                case "3":
                SolicitudesDAO.actualizar(1, "Problema de acceso a la aplicación", "Descripcion modificada");
                break;
                case "4":
                lista=SolicitudesDAO.obtenerListaOrdenadoPorFecha();
                if(lista.length==0){
                    System.out.println("No hay solicitudes que mostrar");
                }else{
                    for(Object o:lista){
                        System.out.println(((Solicitudes)o).toString());
                    }
                }
                break;
                case "5":
                SolicitudesDAO.estaEnCurso(1);
                break;
                case "6":
                SolicitudesDAO.marcarPendiente(1);
                break;
                case "7":
                SolicitudesDAO.marcarEnCurso(1);
                break;
                case "8":
                SolicitudesDAO.marcarFinalizada(1);
                break;
                case "9":
                SolicitudesDAO.eliminar(1);
                break;
                case "exit":
                exit=true;
                break;
                default:
                    System.out.println("Input no valido");
            }
        }
    }

    */

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
        switch(input){
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
        System.out.println("Seleccionar opcion");
        System.out.println("1. Crear nueva solicitud");
        System.out.println("2. Ver todas las solicitudes");
        System.out.println("3. Modificar una solicitud");
        input = new Scanner(System.in).next();
        switch(input){
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
