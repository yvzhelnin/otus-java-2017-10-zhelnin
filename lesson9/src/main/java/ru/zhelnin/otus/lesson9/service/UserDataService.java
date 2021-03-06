package ru.zhelnin.otus.lesson9.service;

import ru.zhelnin.otus.lesson9.model.UserData;

import java.sql.SQLException;
import java.util.Collection;

public interface UserDataService {

    void createUser(UserData user) throws SQLException;

    void saveUser(UserData user) throws SQLException;

    UserData getUserById(long id) throws SQLException;

    Collection<UserData> getAll() throws SQLException;
}
