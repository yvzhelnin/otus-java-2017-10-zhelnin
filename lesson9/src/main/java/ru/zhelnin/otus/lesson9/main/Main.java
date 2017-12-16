package ru.zhelnin.otus.lesson9.main;

import ru.zhelnin.otus.lesson9.model.UserData;
import ru.zhelnin.otus.lesson9.service.UserService;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
        UserService.createUser(new UserData("Ivan", 45));
        UserService.getAll().stream().map(UserData::toString).forEach(System.out::println);

        UserData user = UserService.getUserById(1L);
        System.out.println(user.toString());

        user.setAge(56);
        UserService.saveUser(user);
        System.out.println(UserService.getUserById(1L).toString());
    }
}
