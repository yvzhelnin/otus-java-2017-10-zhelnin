package ru.zhelnin.otus.lesson9.database.connection;

import com.sun.xml.internal.ws.Closeable;
import ru.zhelnin.otus.lesson9.properties.AppProperties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectionWrapper implements Closeable {

    private final Connection connection;

    private static final String databaseUrl = AppProperties.getProperty("postgres.jdbc.url");
    private static final String databaseUsername = AppProperties.getProperty("postgres.jdbc.username");
    private static final String databasePassword = AppProperties.getProperty("postgres.jdbc.password");

    public ConnectionWrapper() throws SQLException {
        DriverManager.registerDriver(new org.postgresql.Driver());
        connection = DriverManager.getConnection(databaseUrl, databaseUsername, databasePassword);
    }

    public PreparedStatement prepareStatement(String query) throws SQLException {
        return connection.prepareStatement(query);
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("Couldn't close connection");
        }
    }
}
