// package main.java.com.example;

// import java.sql.Connection;
// import java.sql.DriverManager;
// import java.sql.Statement;

// public class UserService {

//     // SECURITY ISSUE: Hardcoded credentials
//     private String password = "admin123";

//     // VULNERABILITY: SQL Injection
//     public void findUser(String username) throws Exception {

//         Connection conn =
//             DriverManager.getConnection("jdbc:mysql://localhost/db",
//                     "root", password);

//         Statement st = conn.createStatement();

//         String query =
//             "SELECT * FROM users WHERE name = '" + username + "'";

//         st.executeQuery(query);
//     }
//     // EVEN WORSE: another SQL injection
//         public void deleteUser(String username) throws Exception {
//         Connection conn =
//         DriverManager.getConnection("jdbc:mysql://localhost/db",
//         "root", password);
//         Statement st = conn.createStatement();
//         String query =
//         "DELETE FROM users WHERE name = '" + username + "'";
//         st.execute(query);
//         }


//     // SMELL: Unused method
//     public void notUsed() {
//         System.out.println("I am never called");
//     }
// }
package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserService {

    // Credentials should come from environment variables
    private static final String DB_URL =
            System.getenv("DB_URL");      // e.g. jdbc:mysql://localhost/db
    private static final String DB_USER =
            System.getenv("DB_USER");     // e.g. root
    private static final String DB_PASSWORD =
            System.getenv("DB_PASSWORD"); // no hardcoding

    public void findUser(String username) throws SQLException {

        String query = "SELECT * FROM users WHERE name = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, username);
            ps.executeQuery();
        }
    }

    public void deleteUser(String username) throws SQLException {

        String query = "DELETE FROM users WHERE name = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, username);
            ps.executeUpdate();
        }
    }
}
