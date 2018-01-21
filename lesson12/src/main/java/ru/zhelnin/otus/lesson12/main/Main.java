package ru.zhelnin.otus.lesson12.main;

import ru.zhelnin.otus.lesson12.generator.DataGenerator;
import ru.zhelnin.otus.lesson12.service.UserDataService;
import ru.zhelnin.otus.lesson12.service.impl.UserDataServiceImpl;

import java.io.IOException;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException, IOException {
        try (UserDataService service = new UserDataServiceImpl()) {
            DataGenerator.execute(service);
        }
    }
}
