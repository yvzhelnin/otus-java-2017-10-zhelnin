package ru.zhelnin.otus.lesson16.frontend.socket;

import ru.zhelnin.otus.lesson16.core.message.Message;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FrontendSocketClient {

    private static final Logger logger = Logger.getLogger(FrontendSocketClient.class.getName());

    private final FrontendMessageWorker client;

    public FrontendSocketClient(FrontendMessageWorker client) throws IOException {
        this.client = client;
    }

    @SuppressWarnings("InfiniteLoopStatement")
    private void start() throws Exception {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(() -> {
            try {
                while (true) {
                    Object message = client.take();
                    logger.info("Message received: " + message.toString());
                }
            } catch (InterruptedException e) {
                logger.log(Level.SEVERE, e.getMessage());
            }
        });
        client.close();
        executorService.shutdown();
    }

    public void sendMessage(Message message) {
        client.send(message);
    }
}
