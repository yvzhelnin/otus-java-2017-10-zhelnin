package ru.zhelnin.otus.lesson16.dbserver.generator.utils;

import ru.zhelnin.otus.lesson16.dbserver.model.AddressData;
import ru.zhelnin.otus.lesson16.dbserver.model.PhoneData;
import ru.zhelnin.otus.lesson16.dbserver.model.UserData;
import ru.zhelnin.otus.lesson16.dbserver.service.UserDataService;
import ru.zhelnin.otus.lesson16.dbserver.util.Constants;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class GeneratorThread implements Runnable {

    private final Logger logger = Logger.getLogger(GeneratorThread.class.getName());

    private final UserDataService userDataService;

    private static long firstId = 1L;
    private static long currentId = firstId;

    public GeneratorThread(UserDataService userDataService) {
        this.userDataService = userDataService;
    }

    @Override
    @SuppressWarnings("InfiniteLoopStatement")
    public void run() {
        while (true) {
            try {
                handleData();
                Thread.sleep(Constants.GENERATOR_PAUSE);
            } catch (SQLException | InterruptedException e) {
                logger.severe("An error has been occured");
            }
        }
    }

    private void handleData() throws SQLException {
        userDataService.getAll();
        generateUserData(userDataService);
        userDataService.getUserById(currentId++);
        getEvictedElement();
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
