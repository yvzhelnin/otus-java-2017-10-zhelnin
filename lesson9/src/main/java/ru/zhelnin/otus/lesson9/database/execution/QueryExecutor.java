package ru.zhelnin.otus.lesson9.database.execution;

import ru.zhelnin.otus.lesson9.database.connection.ConnectionWrapper;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class QueryExecutor {

    private final ConnectionWrapper connection;

    public QueryExecutor(ConnectionWrapper connection) {
        this.connection = connection;
    }

    public void executePrepared(String query, PreparedHandler handler) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            handler.prepare(statement);
        }
    }

    public <T> T executeGet(String query, PreparedHandler preparedHandler, ResultHandler<T> resultHandler) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            preparedHandler.prepare(statement);

            return resultHandler.handle(statement.getResultSet());
        }
    }
}
