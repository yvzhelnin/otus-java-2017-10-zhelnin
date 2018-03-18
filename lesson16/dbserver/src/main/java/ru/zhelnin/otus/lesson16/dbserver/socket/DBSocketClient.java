package ru.zhelnin.otus.lesson16.dbserver.socket;

import ru.zhelnin.otus.lesson16.core.message.Message;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBSocketClient {

    private final Logger logger = Logger.getLogger(DBSocketClient.class.getName());

    private final DBMessageWorker client;

    public DBSocketClient(DBMessageWorker client) throws IOException {
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
                e.printStackTrace();
            }
        });
        client.close();
        executorService.shutdown();
    }

    public void sendMessage(Message message) {
        logger.info("Sending message: " + message.toString());
        client.send(message);
    }
}
