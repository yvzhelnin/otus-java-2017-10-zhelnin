package ru.zhelnin.otus.lesson16.frontend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.zhelnin.otus.lesson16.core.util.BaseConstants;
import ru.zhelnin.otus.lesson16.frontend.socket.FrontendMessageWorker;
import ru.zhelnin.otus.lesson16.frontend.socket.FrontendSocketClient;

import java.io.IOException;
import java.net.Socket;

@Configuration
public class FrontendSpringConfiguration {

    @Bean
    public Socket socket() throws IOException {
        return new Socket(BaseConstants.SOCKET_HOST, BaseConstants.FRONTDEND_SOCKET_PORT);
    }

    @Bean(initMethod = "init")
    public FrontendMessageWorker frontendMessageWorker() throws IOException{
        return new FrontendMessageWorker(socket());
    }

    @Bean(initMethod = "start")
    public FrontendSocketClient socketClient() throws IOException {
        return new FrontendSocketClient(frontendMessageWorker());
    }
}
