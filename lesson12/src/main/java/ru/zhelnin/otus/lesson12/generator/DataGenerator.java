package ru.zhelnin.otus.lesson12.generator;

import ru.zhelnin.otus.lesson12.generator.utils.GeneratorThread;
import ru.zhelnin.otus.lesson12.service.UserDataService;

public class DataGenerator {

    public static void execute(UserDataService userDataService) {
        new GeneratorThread(userDataService).run();
    }
}
