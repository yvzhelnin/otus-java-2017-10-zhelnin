package ru.zhelnin.otus.lesson16.frontend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.zhelnin.otus.lesson16.frontend.socket.FrontendMessageWorker;
import ru.zhelnin.otus.lesson16.frontend.socket.FrontendSocketClient;

import java.io.IOException;
import java.net.Socket;

@Configuration
public class FrontendSpringConfiguration {

    private static final String HOST = "localhost";
    private static final int PORT = 5050;

    @Bean(initMethod = "init")
    public FrontendMessageWorker frontendMessageWorker() throws IOException{
        return new FrontendMessageWorker(new Socket(HOST, PORT));
    }

    @Bean(initMethod = "start")
    public FrontendSocketClient socketClient() throws IOException {
        return new FrontendSocketClient(frontendMessageWorker());
    }
}