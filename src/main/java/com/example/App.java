package com.example;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App {

    private static final Logger logger = Logger.getLogger(App.class.getName());

    public static void main(String[] args) {

        Calculator calc = new Calculator();
        int result = calc.calculate(10, 5, "add");
        logger.info(() -> String.format("Calculation result: %d", result));

        UserService service = new UserService();

        try {
            boolean exists = service.findUser("admin");

            if (exists) {
                logger.info(() -> String.format("User '%s' found.", "admin"));

                // ⚠ Dangerous operation — do only if required
                boolean deleted = service.deleteUser("admin");

                if (deleted) {
                    logger.info(() -> String.format("User '%s' deleted successfully.", "admin"));
                } else {
                    logger.warning(() -> String.format("User '%s' could not be deleted.", "admin"));
                }

            } else {
                logger.warning(() -> String.format("User '%s' not found.", "admin"));
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Database error occurred", e);
        }
    }
}
