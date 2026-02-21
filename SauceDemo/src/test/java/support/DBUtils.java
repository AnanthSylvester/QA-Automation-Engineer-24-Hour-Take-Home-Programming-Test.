//package support;
//
//import config.ConfigReader;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.Statement;
//
//public class DBUtils {
//
//    private static Connection connection;
//
//    public static void connect() throws Exception {
//        connection = DriverManager.getConnection(
//                ConfigReader.get("db.url"),
//                ConfigReader.get("db.username"),
//                ConfigReader.get("db.password")
//        );
//    }
//
//    public static ResultSet executeQuery(String query) throws Exception {
//        Statement statement = connection.createStatement();
//        return statement.executeQuery(query);
//    }
//
//    public static void close() throws Exception {
//        if (connection != null) {
//            connection.close();
//        }
//    }
//}




package support;

import config.ConfigReader;

import java.sql.*;

public class DBUtils {

    private static Connection connection;

    /**
     * Establish database connection using config values
     */
    public static void connect() {
        try {
            String url = ConfigReader.get("db_url");
            String username = ConfigReader.get("db_username");
            String password = ConfigReader.get("db_password");

            connection = DriverManager.getConnection(url, username, password);

        } catch (SQLException e) {
            throw new RuntimeException("❌ DB Connection Failed", e);
        }
    }

    /**
     * Execute SELECT query
     */
    public static ResultSet executeQuery(String query) {
        try {
            Statement statement = connection.createStatement();
            return statement.executeQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException("❌ Query Execution Failed", e);
        }
    }

    /**
     * Execute INSERT / UPDATE / DELETE
     */
    public static int executeUpdate(String query) {
        try {
            Statement statement = connection.createStatement();
            return statement.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException("❌ Update Execution Failed", e);
        }
    }

    /**
     * Close DB connection safely
     */
    public static void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException("❌ DB Close Failed", e);
        }
    }
}