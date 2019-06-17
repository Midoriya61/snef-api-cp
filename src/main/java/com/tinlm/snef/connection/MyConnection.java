package com.tinlm.snef.connection;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection implements Serializable {
    public static Connection myConnection() throws SQLException, ClassNotFoundException{
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//        String url = "jdbc:sqlserver://localhost:1433;databaseName=SNEF_DEMO";
//        Connection con = DriverManager.getConnection(url, "sa", "chaulenba");
        String url = "jdbc:sqlserver://localhost:1433;databaseName=NEW_SNEF";
        Connection con = DriverManager.getConnection(url, "sa", "chaulenba");
        return con;
    }
}

