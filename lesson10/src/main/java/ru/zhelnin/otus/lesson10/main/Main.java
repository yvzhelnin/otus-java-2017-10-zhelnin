package ru.zhelnin.otus.lesson10.main;

import ru.zhelnin.otus.lesson10.model.AddressData;
import ru.zhelnin.otus.lesson10.model.UserData;
import ru.zhelnin.otus.lesson10.service.UserDataService;
import ru.zhelnin.otus.lesson10.service.impl.UserDataServiceImpl;

import java.io.IOException;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException, IOException {
        try(UserDataService service = new UserDataServiceImpl()) {
            service.createUser(new UserData("Ivan", 45, new AddressData("Lenina str.")));

            service.getAll().stream().map(UserData::toString).forEach(System.out::println);

/*            UserData user = service.getUserById(1L);
            System.out.println(user.toString());

            user.setAge(56);
            service.saveUser(user);
            System.out.println(service.getUserById(1L).toString());*/
        }
    }
}
