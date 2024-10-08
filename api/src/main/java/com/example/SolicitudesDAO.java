package com.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Comparator;
import com.example.Solicitudes.EstadoSolicitud;

public class SolicitudesDAO {
    static PreparedStatement ps;
    static String sql;
    static ResultSet rs;
    static int id;

    // METODO crear (CREATE) retorna TRUE si la solicitud se crea correctamente en la base de datos, false en caso contrario
    // Recibe el nombre del solicitante, el tema y la descripcion de la solicitud como parametros
    public static boolean crear(String nombreSolicitante,String tema,String descripcion){
        boolean done=false;
        try (Connection cn = Conexion.establecer()){
            sql = "SELECT id FROM usuarios WHERE username=?;";
            ps = cn.prepareStatement(sql);
            ps.setString(1, nombreSolicitante);
            rs = ps.executeQuery();
            if(rs.next()){
                id = rs.getInt(1);
                sql = "INSERT INTO solicitudes (id_usuario,fecha,tema,descripcion,estado,fecha_asistencia) VALUES(?,?,?,?,?,null);";
                ps = cn.prepareStatement(sql);
                ps.setInt(1, id);
                ps.setString(2, LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                ps.setString(3, tema);
                ps.setString(4, descripcion);
                ps.setString(5, String.valueOf(EstadoSolicitud.PENDIENTE));
                if(ps.executeUpdate()==1){
                    System.out.println("Solicitud insertada correctamente");
                    done=true;
                }
            }else{
                System.out.println("Nombre de usuario no registrado");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return done;
    }

    // METODO actualizar (UPDATE) retorna TRUE si la solicitud se actualiza correctamente en la base de datos, FALSE en caso contrario
    // Recibe el id, el tema y la descripcion de la solicitud como parametros
    public static boolean actualizar(int id,String tema,String descripcion){
        boolean done = false;
        try(Connection cn=Conexion.establecer()){
            sql = "UPDATE solicitudes SET tema = ?,descripcion = ? WHERE id = ?";
            ps=cn.prepareStatement(sql);
            ps.setString(1, tema);
            ps.setString(2, descripcion);
            ps.setInt(3, id);
            if(ps.executeUpdate()==1){
                System.out.println("Solicitud modificada correctamente");
                done=true;
            }else{
                System.out.println("No ha habido modificaciones");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return done;
    }

    // METODO obtenerPorId (GET(ID)) retorna un objeto de tipo Solicitudes.
    // Este objeto tendra como atributos los datos devueltos por la consulta a la base de datos
    // Si el id de la solicitud no existe en la base de datos (es decir, la consulta no tiene resultados) retorna NULL
    // Recibe el id de la solicitud como parametro

    public static Solicitudes obtenerPorId(int id){
        Solicitudes s=null;
        try (Connection cn=Conexion.establecer()){
            sql = "SELECT * FROM solicitudes WHERE id = ?;";
            ps=cn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if(rs.next()){
                s = new Solicitudes(rs.getString("descripcion"),
                                     rs.getDate("fecha").toLocalDate(),
                                     rs.getInt("id"),
                                     rs.getInt("id_usuario"),
                                     //rs.getString("prioridad"),
                                     rs.getString("tema"),
                                     EstadoSolicitud.valueOf(rs.getString("estado")),
                                     rs.getDate("fecha_asistencia")==null?null:rs.getDate("fecha_asistencia").toLocalDate());
            }else{
                System.out.println("No hay registros para el id "+id);
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return s;
    }

    //METODO obtenerLista (GET) retorna un array de tipo Solicitudes
    //que contendra todos los registros devueltos por la consulta
    //cada uno almacenados en un objeto de tipo Solicitudes en sus respectivos atributos
    //retorna un array de longitud 0 si la consulta no tiene resultados
    public static Solicitudes[] obtenerLista(){
        Solicitudes[]lista=new Solicitudes[0];
        try (Connection cn=Conexion.establecer()){
            sql = "SELECT * FROM solicitudes;";
            ps=cn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                lista=Arrays.copyOf(lista,lista.length+1);
                lista[lista.length-1]=new Solicitudes(rs.getString("descripcion"),
                                     rs.getDate("fecha").toLocalDate(),
                                     rs.getInt("id"),
                                     rs.getInt("id_usuario"),
                                     //rs.getString("prioridad"),
                                     rs.getString("tema"),
                                     EstadoSolicitud.valueOf(rs.getString("estado")),
                                     rs.getDate("fecha_asistencia")==null?null:rs.getDate("fecha_asistencia").toLocalDate());
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return lista;
    }

    // METODO obtenerListaOrdenadoPorFecha retorna un array de tipo solicitudes
    // Simplemente cogera el resultado de ejecutar el metodo obtenerLista() y le dara la vuelta
    // mostrando primero las solicitudes mas antiguas
    public static Solicitudes[] obtenerListaOrdenadoPorFecha(){
        Solicitudes[]lista=SolicitudesDAO.obtenerLista();
        Arrays.sort(lista, Comparator.comparing(Solicitudes::getFecha).reversed().thenComparing(Solicitudes::getId).reversed());
        return lista;
    }

    //METODO estaEnCurso retorna TRUE si el estado de una solicitud es EN_CURSO,FALSE en caso contrario
    //Recibe como parametro el id de la solicitud de la cual se quiere verificar su estado
    public static boolean estaEnCurso(int id){
        boolean b = false;
        try (Connection cn = Conexion.establecer()){
            Solicitudes s = SolicitudesDAO.obtenerPorId(id);
            if(s!=null){
                System.out.println("Estado de la solicitud: "+String.valueOf(s.getEstado()));
                if(s.getEstado()==EstadoSolicitud.EN_CURSO){
                    b = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return b;
    }

    //METODO eliminar (DELETE) retorna TRUE si se elimina el registro en la base de datos,FALSE en caso contrario
    //Recibe como parametro el id de la solicitud que se quiere eliminar
    //unicamente se podra eliminar si el estado de la solicitud es FINALIZADA
    public static boolean eliminar(int id){
        boolean done = false;
        Solicitudes s = SolicitudesDAO.obtenerPorId(id);
        if(s!=null){
            if(s.getEstado()==EstadoSolicitud.FINALIZADA){
                sql = "DELETE FROM solicitudes WHERE id = ?;";
                try(Connection cn = Conexion.establecer()) {
                    ps=cn.prepareStatement(sql);
                    ps.setInt(1, s.getId());
                    if(ps.executeUpdate()==1){
                        done=true;
                        System.out.println("Solicitud eliminada de la base de datos correctamente");
                    }else{
                        System.out.println("No ha habido modificaciones");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else{
                System.out.println("No se pueden eliminar solicitudes que no se hayan registrado como finalizadas");
            }
        }
        return done;
    }

    //METODO marcarEnCurso retorna TRUE si modifica el registro en la base de datos,FALSE en caso contrario
    //recibe como parametro el id de la solicitud que se quiere actualizar su estado a EN_CURSO
    //Ademas registrara en la base de datos la fecha en la que se hizo esta modificacion en el campo fecha_asistencia
    public static boolean marcarEnCurso(int id){
        try (Connection cn = Conexion.establecer()){
            return marcar(EstadoSolicitud.EN_CURSO,id,cn)&&actualizarFechaAsistencia(id,cn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
        
    }

    //METODO PRIVADO actualizarFechaAsistencia
    //Simplemente se encargara de actualizar la fecha de asistencia de manera interna
    //cuando se haya cambiado el estado de una solicitud a EN_CURSO
    private static boolean actualizarFechaAsistencia(int id,Connection cn) throws SQLException{
        boolean done = false;
            sql = "UPDATE solicitudes SET fecha_asistencia = ? WHERE id = ? AND estado = ?;";
            ps=cn.prepareStatement(sql);
            ps.setInt(2, id);
            ps.setString(1, LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            ps.setString(3, String.valueOf(EstadoSolicitud.EN_CURSO));
            if(ps.executeUpdate()==1){
                done = true;
                System.out.println("Fecha de asistencia de la solicitud actualizada");
            }
        return done;
    }

    //METODO marcarFinalizada retorna TRUE si modifica el registro en la base de datos,FALSE en caso contrario
    //recibe como parametro el id de la solicitud que se quiere actualizar su estado a FINALIZADA
    public static boolean marcarFinalizada(int id){
        try (Connection cn = Conexion.establecer()){
            return marcar(EstadoSolicitud.FINALIZADA,id,cn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //METODO marcarPendiente retorna TRUE si modifica el registro en la base de datos,FALSE en caso contrario
    //recibe como parametro el id de la solicitud que se quiere actualizar su estado a FINALIZADA
    public static boolean marcarPendiente(int id){
        try (Connection cn = Conexion.establecer()){
            return marcar(EstadoSolicitud.PENDIENTE,id,cn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //METODO PRIVADO marcar retorna TRUE si modifica el registro en la base de datos,FALSE en caso contrario
    //Se encarga de manejar los posibles estados de las solicitudes de manera interna
    private static boolean marcar (EstadoSolicitud estado,int id,Connection cn) throws SQLException{
        boolean done = false;
            Solicitudes s = SolicitudesDAO.obtenerPorId(id);
            if(s!=null){
                sql = "UPDATE solicitudes SET estado = ? WHERE id = ?;";
                ps=cn.prepareStatement(sql);
                ps.setString(1, String.valueOf(estado));
                ps.setInt(2, id);
                if(ps.executeUpdate()==1){
                    done = true;
                    System.out.println("Solicitud con id "+id+" marcada como "+String.valueOf(estado));
                }else{
                    System.out.println("No ha habido modificaciones");
                }
            }
        
        return done;
    }

    
}
