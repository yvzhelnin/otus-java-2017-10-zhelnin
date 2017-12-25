package ru.zhelnin.otus.lesson9.main;

import ru.zhelnin.otus.lesson9.model.UserData;
import ru.zhelnin.otus.lesson9.service.impl.UserDataServiceImpl;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
        new UserDataServiceImpl().createUser(new UserData("Ivan", 45));
        new UserDataServiceImpl().getAll().stream().map(UserData::toString).forEach(System.out::println);

        UserData user = new UserDataServiceImpl().getUserById(1L);
        System.out.println(user.toString());

        user.setAge(56);
        new UserDataServiceImpl().saveUser(user);
        System.out.println(new UserDataServiceImpl().getUserById(1L).toString());
    }
}
