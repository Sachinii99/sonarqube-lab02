package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserService {

    private static final String DB_URL = System.getenv("DB_URL");
    private static final String DB_USER = System.getenv("DB_USER");
    private static final String DB_PASSWORD = System.getenv("DB_PASSWORD");

    // Validate DB config once
    static {
        if (DB_URL == null || DB_USER == null || DB_PASSWORD == null) {
            throw new IllegalStateException("Database environment variables are not set");
        }
    }

    /**
     * Finds whether a user exists
     */
    public boolean findUser(String username) throws SQLException {

        String query = "SELECT 1 FROM users WHERE name = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, username);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next(); // true if user exists
            }
        }
    }

    /**
     * Deletes a user
     */
    public boolean deleteUser(String username) throws SQLException {

        String query = "DELETE FROM users WHERE name = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, username);
            int rowsAffected = ps.executeUpdate();

            return rowsAffected > 0; // true if deleted
        }
    }
}
