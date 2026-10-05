package studentmanagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    static final String URL =
            "jdbc:mysql://localhost:3306/training";

    static final String USERNAME = "root";

    static final String PASSWORD = "sql123";

    public static Connection getConnection()
            throws SQLException {

        return DriverManager.getConnection(
                URL,
                USERNAME,
                PASSWORD
        );
    }
}