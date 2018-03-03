package ru.zhelnin.otus.lesson16.messageserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.zhelnin.otus.lesson16.messageserver.server.MessageServer;

@Configuration
public class SpringConfiguration {

    @Bean(initMethod = "start")
    public MessageServer messageServer() {
        return new MessageServer();
    }
}
