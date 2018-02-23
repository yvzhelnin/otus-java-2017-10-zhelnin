package ru.zhelnin.otus.lesson15.generator;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import ru.zhelnin.otus.lesson15.generator.utils.GeneratorThread;
import ru.zhelnin.otus.lesson15.service.UserDataService;

public class DataGenerator {

    private final UserDataService userDataService;
    private final ThreadPoolTaskExecutor executor;

    public DataGenerator(UserDataService userDataService, ThreadPoolTaskExecutor executor) {
        this.userDataService = userDataService;
        this.executor = executor;
    }

    public void init() {
        executor.execute(new GeneratorThread(userDataService));
    }
}
