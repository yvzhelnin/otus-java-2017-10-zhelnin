package ru.zhelnin.otus.lesson10.main;

import ru.zhelnin.otus.lesson10.model.AddressData;
import ru.zhelnin.otus.lesson10.model.PhoneData;
import ru.zhelnin.otus.lesson10.model.UserData;
import ru.zhelnin.otus.lesson10.service.UserDataService;
import ru.zhelnin.otus.lesson10.service.impl.UserDataServiceImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws SQLException, IOException {
        try(UserDataService service = new UserDataServiceImpl()) {
            List<PhoneData> phones = Arrays.asList(new PhoneData("99999999999"), (new PhoneData("333333333333")));
            service.createUser(new UserData("Ivan", 45, new AddressData("Lenina str."), phones));

            service.getAll().stream().map(UserData::toString).forEach(System.out::println);

            UserData user = service.getUserById(1L);
            System.out.println(user.toString());

            user.setAge(56);
            service.saveUser(user);
            System.out.println(service.getUserById(1L).toString());

            user.getAddress().setStreet("Karla Marksa str.");
            service.saveUser(user);
            System.out.println(service.getUserById(1L).toString());

            user.getPhones().set(0, new PhoneData("11111111111111111"));
            service.saveUser(user);
            System.out.println(service.getUserById(1L).toString());
        }
    }
}
