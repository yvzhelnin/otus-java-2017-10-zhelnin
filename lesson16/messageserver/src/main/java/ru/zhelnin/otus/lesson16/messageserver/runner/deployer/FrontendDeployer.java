package ru.zhelnin.otus.lesson16.messageserver.runner.deployer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class FrontendDeployer {

    private static final Logger logger = Logger.getLogger(FrontendDeployer.class.getName());

    private static final String SUB_PATH_TO_WAR = "\\frontend\\target\\frontend-1.0-SNAPSHOT.war";

    public static void deploy(String pathPrefix, String pathToTomcat) {
        String pathToWar = pathPrefix + SUB_PATH_TO_WAR;
        try {
            Files.copy(Paths.get(pathToWar), Paths.get(pathToTomcat), REPLACE_EXISTING);
            logger.info("Frontend WAR file was successfully copied to Tomcat deployment directory");

        } catch (IOException e) {
            logger.log(Level.SEVERE, "Couldn't deploy frontend WAR file to Tomcat");
        }
    }
}
