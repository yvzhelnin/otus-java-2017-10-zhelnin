package ru.zhelnin.otus.lesson10.service.dao;

import ru.zhelnin.otus.lesson10.database.connection.ConnectionWrapper;
import ru.zhelnin.otus.lesson10.database.execution.QueryExecutor;
import ru.zhelnin.otus.lesson10.model.UserData;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class UserDataDao {

    private static final String CREATE_QUERY = "INSERT INTO t_user (name, age) VALUES(?, ?)";
    private static final String SAVE_QUERY = "UPDATE t_user SET name = ?, age = ? WHERE id = ?";
    private static final String GET_BY_ID_QUERY = "SELECT u.name, u.age FROM t_user u WHERE id = ?";
    private static final String GET_ALL_QUERY = "SELECT u.id, u.name, u.age FROM t_user u ORDER BY u.id";

    public void createUser(UserData user) throws SQLException {
        try (ConnectionWrapper connection = new ConnectionWrapper()) {
            QueryExecutor executor = new QueryExecutor(connection);
            executor.executePrepared(CREATE_QUERY, statement -> {
                statement.setString(1, user.getName());
                statement.setInt(2, user.getAge());
            });
        }
    }

    public void saveUser(UserData user) throws SQLException {
        try (ConnectionWrapper connection = new ConnectionWrapper()) {
            QueryExecutor executor = new QueryExecutor(connection);
            executor.executePrepared(SAVE_QUERY, statement -> {
                int index = 1;
                statement.setString(index++, user.getName());
                statement.setInt(index++, user.getAge());
                statement.setLong(index, user.getId());
            });
        }
    }

    public UserData getUserById(long id) throws SQLException {
        try (ConnectionWrapper connection = new ConnectionWrapper()) {
            QueryExecutor executor = new QueryExecutor(connection);
            return executor.executeGet(GET_BY_ID_QUERY, statement -> {
                statement.setLong(1, id);
            }, resultSet -> {
                UserData userData = new UserData();
                if (resultSet.next()) {
                    userData = new UserData(id, resultSet.getString("name"), resultSet.getInt("age"));
                }
                return userData;
            });
        }
    }

    public Collection<UserData> getAll() throws SQLException {
        Collection<UserData> users = new ArrayList<>();
        try (ConnectionWrapper connection = new ConnectionWrapper()) {
            QueryExecutor executor = new QueryExecutor(connection);
            return executor.executeGet(GET_ALL_QUERY, null, resultSet -> {
                while (resultSet.next()) {
                    users.add(new UserData(resultSet.getLong("id"), resultSet.getString("name"), resultSet.getInt("age")));
                }
                return users;
            });
        }
    }
}
