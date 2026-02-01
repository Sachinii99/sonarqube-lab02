package com.example;

import java.sql.SQLException;

public class App {

    public static void main(String[] args) {

        Calculator calc = new Calculator();
        System.out.println(calc.calculate(10, 5, "add"));

        UserService service = new UserService();

        try {
            boolean exists = service.findUser("admin");

            if (exists) {
                System.out.println("User 'admin' found.");

                // ⚠ Dangerous operation — do only if required
                boolean deleted = service.deleteUser("admin");

                if (deleted) {
                    System.out.println("User 'admin' deleted successfully.");
                } else {
                    System.out.println("User 'admin' could not be deleted.");
                }

            } else {
                System.out.println("User 'admin' not found.");
            }

        } catch (SQLException e) {
            System.err.println("Database error occurred: " + e.getMessage());
        }
    }
}
