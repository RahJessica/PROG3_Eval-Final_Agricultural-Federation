package org.example.prog3_agriculturalfederation;

import org.example.prog3_agriculturalfederation.config.DatabaseConnection;

import java.sql.Connection;

public class TestConnection {
    public static void main(String[] args) {
        try {
            Connection conn = DatabaseConnection.getConnection();

            if (conn != null && !conn.isClosed()) {
                System.out.println("Connection successful !");
            } else {
                System.out.println("Connection failed !");
            }

        } catch (Exception e) {
            System.out.println(" Error while connecting to database:");
            e.printStackTrace();
        }
    }
}
