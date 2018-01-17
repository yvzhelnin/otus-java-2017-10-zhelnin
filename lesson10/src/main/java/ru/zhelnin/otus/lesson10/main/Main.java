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
            List<PhoneData> phonesIvan = Arrays.asList(new PhoneData("99999999999"), (new PhoneData("333333333333")));
            List<PhoneData> phonesMax = Arrays.asList(new PhoneData("99999999999"), (new PhoneData("333333333333")));
            List<PhoneData> phonesPeter = Arrays.asList(new PhoneData("99999999999"), (new PhoneData("333333333333")));
            List<PhoneData> phonesAlex = Arrays.asList(new PhoneData("99999999999"), (new PhoneData("333333333333")));
            List<PhoneData> phonesAnna = Arrays.asList(new PhoneData("99999999999"), (new PhoneData("333333333333")));
            List<PhoneData> phonesOlga = Arrays.asList(new PhoneData("99999999999"), (new PhoneData("333333333333")));

            service.createUser(new UserData("Ivan", 45, new AddressData("Lenina str."), phonesIvan));
            service.createUser(new UserData("Max", 12, new AddressData("Truda str."), phonesMax));
            service.createUser(new UserData("Peter", 99, new AddressData("Malysheva str."), phonesPeter));
            service.createUser(new UserData("Alex", 30, new AddressData("Engelsa str."), phonesAlex));

            service.printCache();

            service.getUserById(1L);
            service.getUserById(2L);
            service.getUserById(4L);

            service.createUser(new UserData("Anna", 20, new AddressData("Yasnaya str."), phonesAnna));
            service.createUser(new UserData("Olga", 50, new AddressData("Mira str."), phonesOlga));

            service.printCache();

            service.getAllUsersFromCache().stream().map(UserData::toString).forEach(System.out::println);

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
