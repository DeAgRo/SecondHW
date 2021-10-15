package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {


    public static Connection getConnection(){
        final String hostName = "localhost";
        final String dbName = "Somedb";
        final String username = "root";
        final String pass = "12345";
        return getConnection(hostName,dbName,username,pass);
    }
    public static Connection getConnection(String hostName, String dbName, String username, String pass){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String connectionUrl = "jdbc:mysql://" + hostName + ":3306/" + dbName + "?useSSL=false";
        Connection con = null;
        try {
            con = DriverManager.getConnection(connectionUrl,username,pass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
}
