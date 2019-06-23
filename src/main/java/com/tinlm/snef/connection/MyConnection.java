package com.tinlm.snef.connection;

import java.io.Serializable;
import java.sql.*;

public class MyConnection implements Serializable {
    public static Connection myConnection() throws SQLException, ClassNotFoundException{
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//        String url = "jdbc:sqlserver://localhost:1433;databaseName=SNEF_DEMO";
//        Connection con = DriverManager.getConnection(url, "sa", "chaulenba");
        String url = "jdbc:sqlserver://localhost:1433;databaseName=SNEF_Part2";
        Connection con = DriverManager.getConnection(url, "sa", "chaulenba");
        return con;
    }

    public static void closeConnection(ResultSet rs, PreparedStatement stm, Connection con) {
        try {
            if (rs != null){
                rs.close();
            }
            if (stm !=null){
                stm.close();
            }
            if (con!=null){
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

