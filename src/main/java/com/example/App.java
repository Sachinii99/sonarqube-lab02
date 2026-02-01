package com.example;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App {

    // Create a logger for this class
    private static final Logger logger = Logger.getLogger(App.class.getName());

    public static void main(String[] args) {

        Calculator calc = new Calculator();
        logger.info("Calculation result: " + calc.calculate(10, 5, "add"));

        UserService service = new UserService();

        try {
            boolean exists = service.findUser("admin");

            if (exists) {
                logger.info("User 'admin' found.");

                // ⚠ Dangerous operation — do only if required
                boolean deleted = service.deleteUser("admin");

                if (deleted) {
                    logger.info("User 'admin' deleted successfully.");
                } else {
                    logger.warning("User 'admin' could not be deleted.");
                }

            } else {
                logger.warning("User 'admin' not found.");
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Database error occurred", e);
        }
    }
}
