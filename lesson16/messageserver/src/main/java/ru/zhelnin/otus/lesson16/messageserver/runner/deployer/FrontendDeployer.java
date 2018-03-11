package ru.zhelnin.otus.lesson16.messageserver.runner.deployer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class FrontendDeployer {

    private static final Logger logger = Logger.getLogger(FrontendDeployer.class.getName());

    private static final String SUB_PATH_TO_WAR = "\\frontend\\target\\frontend-1.0-SNAPSHOT.war";
    private static final String PATH_TO_TOMCAT = "C:\\Program Files\\Apache Software Foundation\\Tomcat 8.5\\webapps\\frontend.war";

    public static void deploy(String pathPrefix) {
        String pathToWar = pathPrefix + SUB_PATH_TO_WAR;
        try {
            Files.copy(Paths.get(pathToWar), Paths.get(PATH_TO_TOMCAT), REPLACE_EXISTING);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Couldn't deploy frontend WAR file to Tomcat");
        }
        logger.info("Frontend WAR file was successfully copied to Tomcat deployment directory");
    }
}
