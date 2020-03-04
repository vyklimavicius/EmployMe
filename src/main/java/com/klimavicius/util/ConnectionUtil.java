package com.klimavicius.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class ConnectionUtil {

    final static Logger logger = Logger.getLogger(ConnectionUtil.class);
    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/EmployMe";
        String username = "postgres";
        String password = "Cotiweco1";

        if (connection == null || connection.isClosed()) {

            // register connection
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException ex) {
                logger.error("Error: unable to load driver class!", ex);
                // System.out.println("Error: unable to load driver class!");
                System.exit(1);
            }
            // Start connection
            connection = DriverManager.getConnection(url, username, password);
        }

        return connection;
    }

    // public static Connection getConnection() throws SQLException{
    // String url = System.getenv("JDBC_DB_HOST");
    // String username = System.getenv("JDBC_DB_USER");
    // String password = System.getenv("JDBC_DB_PASSWORD");

    // if(connection == null || connection.isClosed()) {
    // //Start connection
    // connection = DriverManager.getConnection(url, username, password);
    // }

    // return connection;
    // }

    // check

    // try {
    // Connection c = ConnectionUtil.getConnection();
    // String driverName = c.getMetaData().getDriverName();
    // System.out.println(driverName);
    // } catch (SQLException e) {
    // e.printStackTrace();
    // }
}