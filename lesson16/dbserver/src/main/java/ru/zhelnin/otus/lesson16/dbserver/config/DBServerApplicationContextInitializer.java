package ru.zhelnin.otus.lesson16.dbserver.config;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

public class DBServerApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
        String operationSystem = System.getProperty("os.name");
        String profile = (operationSystem.toLowerCase().startsWith("windows")) ? "windows" : "other";
        ConfigurableEnvironment environment = configurableApplicationContext.getEnvironment();
        environment.addActiveProfile(profile);
    }
}
