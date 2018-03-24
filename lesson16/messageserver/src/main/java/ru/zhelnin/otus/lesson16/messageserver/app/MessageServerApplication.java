package ru.zhelnin.otus.lesson16.messageserver.app;

import ru.zhelnin.otus.lesson16.messageserver.runner.DBServerRunner;
import ru.zhelnin.otus.lesson16.messageserver.runner.deployer.FrontendDeployer;
import ru.zhelnin.otus.lesson16.messageserver.server.MessageServer;

import java.io.File;

public class MessageServerApplication {

    private static final String PATH_PREFIX = new File("").getAbsolutePath();
    private static final String PATH_TO_TOMCAT1 = "C:\\Program Files\\Apache Software Foundation\\Tomcat 8.5\\webapps\\frontend.war";
    private static final String PATH_TO_TOMCAT2 = "C:\\Program Files\\Apache Software Foundation\\Tomcat 8.0\\webapps\\frontend.war";

    public static void main(String[] args) {
        MessageServer messageServer = new MessageServer();
        messageServer.start();
        DBServerRunner.run(PATH_PREFIX);
        DBServerRunner.run(PATH_PREFIX);
        FrontendDeployer.deploy(PATH_PREFIX, PATH_TO_TOMCAT1);
        FrontendDeployer.deploy(PATH_PREFIX, PATH_TO_TOMCAT2);
    }
}
