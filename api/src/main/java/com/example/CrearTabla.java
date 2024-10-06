package com.example;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CrearTabla {
    static Connection cn;
    static PreparedStatement ps;
    public static void usuarios() throws SQLException{
        cn=Conexion.establecer();
        ps=cn.prepareStatement(
            "CREATE TABLE IF NOT EXISTS usuarios ("
            + "id INT PRIMARY KEY AUTO_INCREMENT,"
            + "username VARCHAR(50) UNIQUE,"
            + "password VARCHAR(100),"
            + "rol VARCHAR(20)"
            + ");");
        ps.executeUpdate();
        System.out.println("Tabla usuarios creada");
    }
    public static void solicitudes() throws SQLException{
        cn=Conexion.establecer();
        ps=cn.prepareStatement(
            "CREATE TABLE IF NOT EXISTS solicitudes ("
            + "id INT PRIMARY KEY AUTO_INCREMENT,"
            + "id_usuario INT,"
            + "fecha DATE,"
            + "tema VARCHAR(100),"
            + "descripcion TEXT,"
            + "prioridad VARCHAR(20),"
            + "FOREIGN KEY (id_usuario) REFERENCES usuarios(id)"
            + ");");
        ps.executeUpdate();
        System.out.println("Tabla solicitudes creada");
    }
    public static void comentarios() throws SQLException{
        cn=Conexion.establecer();
        ps=cn.prepareStatement(
            "CREATE TABLE IF NOT EXISTS comentarios("
            + "id INT PRIMARY KEY AUTO_INCREMENT,"
            + "id_solicitud INT,"
            + "comentario TEXT,"
            + "FOREIGN KEY (id_solicitud) REFERENCES solicitudes(id)"
            +");");
        ps.executeUpdate();
        System.out.println("Tabla comentarios creada");
    }
}
