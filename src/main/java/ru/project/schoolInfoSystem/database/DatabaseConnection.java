package ru.project.schoolInfoSystem.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static Connection connection = null;

    static {
        String url = "jdbc:postgresql://localhost/school-info-system";
        String user = "postgres";
        String password = "88888";

        try {
            connection = DriverManager.getConnection(url, user, password);

            if (connection != null) {
                System.out.println("Connected to the PostgreSQL server successfully.");
            } else {
                System.out.println("Failed to make connection!");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Get connection to database
     *
     * @return a Connection object
     */
    public static Connection get() {
        return connection;
    }
}