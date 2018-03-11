package ru.zhelnin.otus.lesson16.messageserver.app;

import ru.zhelnin.otus.lesson16.messageserver.runner.DBServerRunner;
import ru.zhelnin.otus.lesson16.messageserver.runner.deployer.FrontendDeployer;
import ru.zhelnin.otus.lesson16.messageserver.server.MessageServer;

import java.io.File;

public class MessageServerApplication {

    private static final String PATH_PREFIX = new File("").getAbsolutePath();

    public static void main(String[] args) {
        MessageServer messageServer = new MessageServer();
        messageServer.start();
        DBServerRunner.run(PATH_PREFIX);
        FrontendDeployer.deploy(PATH_PREFIX);
    }
}
