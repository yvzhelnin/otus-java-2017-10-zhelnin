package ru.zhelnin.otus.lesson13.generator.utils;

import ru.zhelnin.otus.lesson13.model.AddressData;
import ru.zhelnin.otus.lesson13.model.PhoneData;
import ru.zhelnin.otus.lesson13.model.UserData;
import ru.zhelnin.otus.lesson13.service.UserDataService;
import ru.zhelnin.otus.lesson13.util.Constants;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class GeneratorThread implements Runnable {

    private final UserDataService userDataService;

    private static long firstId = 1L;
    private static long currentId = firstId;

    public GeneratorThread(UserDataService userDataService) {
        this.userDataService = userDataService;
    }

    @Override
    public void run() {
        while (true) {
            try {
                handleData();
                Thread.sleep(Constants.GENERATOR_PAUSE);
            } catch (SQLException | InterruptedException e) {
                System.out.println("An error has been occured");
            }
        }
    }

    private void handleData() throws SQLException {
        userDataService.getAll();
        generateUserData(userDataService);
        userDataService.getUserById(currentId++);
        getEvictedElement();
        userDataService.printCache();
    }

    private void getEvictedElement() throws SQLException {
        if (currentId > userDataService.getAllUsersFromCache().size() + firstId) {
            userDataService.getUserById(firstId);
        }
    }

    private static void generateUserData(UserDataService userDataService) throws SQLException {
        userDataService.createUser(new UserData(Randomizer.generateName(), 45, new AddressData(Randomizer.generateStreet()), generatePhones()));
    }

    private static List<PhoneData> generatePhones() {
        return Arrays.asList(new PhoneData(Randomizer.generatePhone()), (new PhoneData(Randomizer.generatePhone())));
    }
}
