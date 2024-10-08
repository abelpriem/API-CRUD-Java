package com.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Comparator;

public class SolicitudesDAO {
    static PreparedStatement ps;
    static String sql;
    static ResultSet rs;
    static int id;

    public static boolean crear(String nombreSolicitante,String tema,String descripcion){
        boolean done=false;
        try (Connection cn = Conexion.establecer()){
            sql = "SELECT id FROM usuarios WHERE username=?;";
            ps = cn.prepareStatement(sql);
            ps.setString(1, nombreSolicitante);
            rs = ps.executeQuery();
            if(rs.next()){
                id = rs.getInt(1);
                sql = "INSERT INTO solicitudes (id_usuario,fecha,tema,descripcion,prioridad,estado) VALUES(?,?,?,?,null,'pendiente');";
                ps = cn.prepareStatement(sql);
                ps.setInt(1, id);
                ps.setString(2, LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                ps.setString(3, tema);
                ps.setString(4, descripcion);
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

    public static Solicitudes obtenerPorId(int id){
        Solicitudes s=null;
        try (Connection cn=Conexion.establecer()){
            sql = "SELECT * FROM solicitudes WHERE id = ?;";
            ps=cn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if(rs.next()){
                s =new Solicitudes(rs.getString("descripcion"),
                                     rs.getDate("fecha").toLocalDate(),
                                     rs.getInt("id"),
                                     rs.getInt("id_usuario"),
                                     rs.getString("prioridad"),
                                     rs.getString("tema"),
                                     rs.getString("estado"));
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return s;
    }

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
                                     rs.getString("prioridad"),
                                     rs.getString("tema"),
                                     rs.getString("estado"));
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return lista;
    }

    public static Solicitudes[] obtenerListaOrdenadoPorFecha(){
        Solicitudes[]lista=SolicitudesDAO.obtenerLista();
        Arrays.sort(lista, Comparator.comparing(Solicitudes::getFecha).reversed().thenComparing(Solicitudes::getId).reversed());
        return lista;
    }
}
