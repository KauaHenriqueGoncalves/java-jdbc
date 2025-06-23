package com.java.java.utils;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * The {@code ConnectionFactory} class is responsible for creating and providing
 * a database connection using configuration values loaded from a .env file.
 *
 * <p>This implementation uses PostgreSQL as the database and expects the following
 * environment variables to be defined in the .env file:
 * <ul>
 *     <li>DB_user</li>
 *     <li>DB_password</li>
 *     <li>DB_host</li>
 *     <li>DB_port</li>
 *     <li>DB_name</li>
 * </ul>
 */
public class ConnectionFactory {

    private final Dotenv dotenv = Dotenv.load();
    private final String USER = dotenv.get("DB_user");
    private final String PASSWORD = dotenv.get("DB_password");
    private final String HOST = dotenv.get("DB_host");
    private final String PORT = dotenv.get("DB_port");
    private final String DATABASE = dotenv.get("DB_name");

    /**
     * Default constructor for {@code ConnectionFactory}.
     */
    public ConnectionFactory() {}

    /**
     * Establishes and returns a new connection to the PostgreSQL database
     * using credentials and connection details defined in the environment file.
     *
     * @return a valid {@link Connection} object
     * @throws RuntimeException if the JDBC driver is not found or a connection cannot be established
     */
    public Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://" + HOST + ":" + PORT + "/" + DATABASE + "?sslmode=require";
            return DriverManager.getConnection(url, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
