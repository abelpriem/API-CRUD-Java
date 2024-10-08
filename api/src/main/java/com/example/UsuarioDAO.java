package com.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UsuarioDAO {
    
    static PreparedStatement ps;
    static ResultSet rs;
    static String sql;

    public static boolean Login (String username, String password){
        sql = "select * from usuarios where username ? and password ?";

        try(Connection cn = Conexion.establecer()){
            ps = cn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            
            rs = ps.executeQuery();

            if(rs.next()){
                int id = rs.getInt("id");
                String rol = rs.getString("rol");

                return true;
            }else {
                return false;
            }

        }catch(SQLException e){
            e.printStackTrace();
        }


    }

    public static boolean isAdmin(String rol){
        if(rol.equals("admin")){
            return true;
        }else{
            return false;
        }
    }
}
