package ru.zhelnin.otus.lesson16.dbserver.socket;

import ru.zhelnin.otus.lesson16.core.message.CacheDataMessage;
import ru.zhelnin.otus.lesson16.core.message.Message;
import ru.zhelnin.otus.lesson16.core.message.RequestMessage;
import ru.zhelnin.otus.lesson16.core.util.BaseConstants;
import ru.zhelnin.otus.lesson16.dbserver.app.CacheService;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBSocketClient {

    private final Logger logger = Logger.getLogger(DBSocketClient.class.getName());

    private final DBMessageWorker client;
    private final CacheService service;

    public DBSocketClient(DBMessageWorker client, CacheService service) throws IOException {
        this.client = client;
        this.service = service;
    }

    @SuppressWarnings("InfiniteLoopStatement")
    private void start() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(() -> {
            try {
                while (true) {
                    logger.info("Taking a message");
                    RequestMessage message = (RequestMessage) client.take();
                    if (message != null && message.getDirection().equals(BaseConstants.DB_DIRECTION) && message.getBackPortNumber() != 0) {
                        logger.info("Got message from server: " + message.toString());
                        sendMessage(new CacheDataMessage(service.getCacheData(), BaseConstants.FRONTEND_DIRECTION, 0, message.getBackPortNumber()));
                    }
                }
            } catch (InterruptedException e) {
                logger.log(Level.SEVERE, e.getMessage());
                e.printStackTrace();
            }
        });
    }

    private void sendMessage(Message message) {
        logger.info("Sending message: " + message.toString());
        client.send(message);
    }
}
