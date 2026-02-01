package com.example;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App {

    private static final Logger logger = Logger.getLogger(App.class.getName());

    // ✅ Define a constant for the username
    private static final String ADMIN_USER = "admin";

    public static void main(String[] args) {

        Calculator calc = new Calculator();
        int result = calc.calculate(10, 5, "add");
        logger.info(() -> String.format("Calculation result: %d", result));

        UserService service = new UserService();

        try {
            boolean exists = service.findUser(ADMIN_USER);

            if (exists) {
                logger.info(() -> String.format("User '%s' found.", ADMIN_USER));

                // ⚠ Dangerous operation — do only if required
                boolean deleted = service.deleteUser(ADMIN_USER);

                if (deleted) {
                    logger.info(() -> String.format("User '%s' deleted successfully.", ADMIN_USER));
                } else {
                    logger.warning(() -> String.format("User '%s' could not be deleted.", ADMIN_USER));
                }

            } else {
                logger.warning(() -> String.format("User '%s' not found.", ADMIN_USER));
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Database error occurred", e);
        }
    }
}
