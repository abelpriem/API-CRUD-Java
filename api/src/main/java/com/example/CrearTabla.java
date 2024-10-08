package com.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CrearTabla {
    static Connection cn;
    static PreparedStatement ps;

    public static void usuarios() throws SQLException {
        cn = Conexion.establecer();
        ps = cn.prepareStatement(
                "CREATE TABLE IF NOT EXISTS usuarios ("
                        + "id INT PRIMARY KEY AUTO_INCREMENT,"
                        + "username VARCHAR(50) UNIQUE,"
                        + "password VARCHAR(100),"
                        + "rol VARCHAR(20)"
                        + ");");
        ps.executeUpdate();
        ps = cn.prepareStatement(
                "INSERT INTO USUARIOS (username,password,rol) VALUES ('Abel',1234,'admin'),('Maria',1234,'admin'),('Adrian',1234,'usuario'),('Oscar',1234,'usuario'),('Roberto',1234,'usuario');");
        ps.executeUpdate();
        System.out.println("Tabla usuarios creada");
    }

    public static void solicitudes() throws SQLException {
        cn = Conexion.establecer();
        ps = cn.prepareStatement(
                "CREATE TABLE IF NOT EXISTS solicitudes ("
                        + "id INT PRIMARY KEY AUTO_INCREMENT,"
                        + "id_usuario INT,"
                        + "fecha DATE,"
                        + "tema VARCHAR(100),"
                        + "descripcion TEXT,"
                        + "estado ENUM('PENDIENTE','EN_CURSO','FINALIZADA'),"
                        + "fecha_asistencia DATE,"
                        + "FOREIGN KEY (id_usuario) REFERENCES usuarios(id)"
                        + ");");
        ps.executeUpdate();
        System.out.println("Tabla solicitudes creada");
    }
}
