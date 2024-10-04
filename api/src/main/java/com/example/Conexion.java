package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    static String user="sa";
    static String password="";
    static String url="jdbc:h2:mem:testdb";

    public static Connection establecer() throws SQLException{
        Connection cn = DriverManager.getConnection(url, user, password);
        return cn;
    }

    public static void iniciarServidor(){
        try {
            org.h2.tools.Server.createTcpServer().start();
            System.out.println("Servidor H2 iniciado en el puerto 8080.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
