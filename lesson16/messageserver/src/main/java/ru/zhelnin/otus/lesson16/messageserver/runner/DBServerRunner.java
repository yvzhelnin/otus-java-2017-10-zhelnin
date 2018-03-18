package ru.zhelnin.otus.lesson16.messageserver.runner;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBServerRunner {

    private static final Logger logger = Logger.getLogger(DBServerRunner.class.getName());

    private static final String JAVA_COMMAND = System.getProperty("java.home") + "/bin/java -jar ";

    public static void run(String pathPrefix) {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.schedule(() -> {
            logger.info("Attempting to start database server process with command " + JAVA_COMMAND);
            try {
                new ProcessRunnerImpl().start(JAVA_COMMAND + pathPrefix + "/dbserver/target/dbserver-1.0-SNAPSHOT.jar");
                logger.info("Database server process was started");
            } catch (IOException e) {
                logger.log(Level.SEVERE, e.getMessage());
            }
        }, 10, TimeUnit.SECONDS);
    }
}
