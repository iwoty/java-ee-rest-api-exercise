package com.codecool.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionToDB {

    public static Connection getConnection() {
        try {
            Class.forName( "org.postgresql.Driver" );
            return DriverManager.getConnection( "jdbc:postgresql://localhost:5432/shopDB",
                    "postgres", "alkoholicyzulicy123" );
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}