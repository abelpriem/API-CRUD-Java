package com.example;

import java.sql.SQLException;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {   
        Object[]lista;
        String input;
        boolean exit=false;
        Conexion.iniciarServidor();
        try{
        CrearTabla.usuarios();
        CrearTabla.solicitudes();
        }catch(SQLException e){
            e.printStackTrace();
        }
        while(!exit){
            System.out.println("Seleccionar opcion");
            System.out.println("1. Crear nueva solicitud");
            System.out.println("2. Ver todas las solicitudes");
            System.out.println("3. Modificar una solicitud");
            System.out.println("4. Ver todas las solicitudes ordenado por fecha");
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
                case "exit":
                exit=true;
                break;
                default:
                    System.out.println("Input no valido");
            }
        }
    }
}
