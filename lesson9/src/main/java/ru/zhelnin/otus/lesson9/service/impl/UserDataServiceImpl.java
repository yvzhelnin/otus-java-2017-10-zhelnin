package ru.zhelnin.otus.lesson9.service.impl;

import ru.zhelnin.otus.lesson9.model.UserData;
import ru.zhelnin.otus.lesson9.service.UserDataService;
import ru.zhelnin.otus.lesson9.service.dao.UserDataDao;

import java.sql.SQLException;
import java.util.Collection;

public class UserDataServiceImpl implements UserDataService {

    public void createUser(UserData user) throws SQLException {
        new UserDataDao().createUser(user);
    }

    public void saveUser(UserData user) throws SQLException {
        new UserDataDao().saveUser(user);
    }

    public UserData getUserById(long id) throws SQLException {
        return new UserDataDao().getUserById(id);
    }

    public Collection<UserData> getAll() throws SQLException {
        return new UserDataDao().getAll();
    }
}
