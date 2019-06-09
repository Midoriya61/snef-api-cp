package com.tinlm.snef.connection;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection implements Serializable {
    public static Connection myConnection() throws SQLException, ClassNotFoundException{
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//        String url = "jdbc:sqlserver://localhost:1433;databaseName=SNEF_DEMO";
//        Connection con = DriverManager.getConnection(url, "sa", "trungnhan137");
        String url = "jdbc:sqlserver://snef.database.windows.net:1433;databaseName=snef;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
        Connection con = DriverManager.getConnection(url, "TinLM", "Chaulen3");
        return con;
    }
}

