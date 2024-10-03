package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Conexion {
    static String user="sa";
    static String password="";
    static String url="jdbc:h2:mem:testdb";

    public static Connection establecer(){
        Connection cn = null;
        try {
            cn = DriverManager.getConnection(url, user, password);
            PreparedStatement ps = cn.prepareStatement(
            "CREATE TABLE IF NOT EXISTS usuarios ("
            + "id INT PRIMARY KEY AUTO_INCREMENT,"
            + "username VARCHAR(50) UNIQUE,"
            + "password VARCHAR(100),"
            + "rol VARCHAR(20)"
            + ");");
            ps.executeUpdate();
            System.out.println("Tabla usuarios creada");
            ps = cn.prepareStatement(
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
            ps = cn.prepareStatement(
            "CREATE TABLE IF NOT EXISTS comentarios("
            + "id INT PRIMARY KEY AUTO_INCREMENT,"
            + "id_solicitud INT,"
            + "comentario TEXT,"
            + "FOREIGN KEY (id_solicitud) REFERENCES solicitudes(id)"
            +");");
            ps.executeUpdate();
            System.out.println("Tabla comentarios creada");

        } catch (SQLException e) {
            System.out.println("Conexion fallida: "+e.getMessage());
        }
        return cn;
    }
}
