package com.tinlm.snef.connection;

import java.io.Serializable;
import java.sql.*;

public class MyConnection implements Serializable {
    private static Connection connection;
    public static Connection myConnection() throws SQLException, ClassNotFoundException{
//        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Class.forName("com.mysql.jdbc.Driver");
//        String url = "jdbc:sqlserver://localhost:1433;databaseName=SNEF_DEMO";
        String url = "jdbc:mysql://snef.cvkbe5tc6xkf.ap-southeast-1.rds.amazonaws.com:3306/snef_part2";
//        Connection con = DriverManager.getConnection(url, "sa", "trungnhan137");

//        String dbName = System.getProperty("SNEF_Part2");
//        String userName = System.getProperty("TinLM");
//        String password = System.getProperty("Chaulen3");
//        String hostname = System.getProperty("snef.cvkbe5tc6xkf.ap-southeast-1.rds.amazonaws.com");
//        String port = System.getProperty("3306");
//        String jdbcUrl = "jdbc:mysql://" + hostname + ":" +
//                port + "/" + dbName + "?user=" + userName + "&password=" + password;

        Connection con = DriverManager.getConnection(url, "TinLM", "Chaulen3");
//        Connection con = DriverManager.getConnection(jdbcUrl);
        return con;
    }


    public static Connection getConnection() {
        if( connection == null ) {
            try {
                connection = myConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
    public static void closeConnection(ResultSet rs, PreparedStatement stm) {
        try {
            if (rs != null){
                rs.close();
            }
            if (stm !=null){
                stm.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

