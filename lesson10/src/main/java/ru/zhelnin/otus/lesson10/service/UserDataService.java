package ru.zhelnin.otus.lesson10.service;

import ru.zhelnin.otus.lesson10.model.UserData;

import java.io.Closeable;
import java.sql.SQLException;
import java.util.Collection;

public interface UserDataService extends Closeable {

    void createUser(UserData user) throws SQLException;

    void saveUser(UserData user) throws SQLException;

    UserData getUserById(long id) throws SQLException;

    Collection<UserData> getAll() throws SQLException;
}
