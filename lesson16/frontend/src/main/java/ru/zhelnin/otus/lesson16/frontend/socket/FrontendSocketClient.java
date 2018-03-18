package ru.zhelnin.otus.lesson16.frontend.socket;

import ru.zhelnin.otus.lesson16.core.message.CacheDataMessage;
import ru.zhelnin.otus.lesson16.core.message.Message;
import ru.zhelnin.otus.lesson16.core.util.BaseConstants;
import ru.zhelnin.otus.lesson16.frontend.endpoint.CacheEndpoint;

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
                    if (message != null && message instanceof CacheDataMessage && ((CacheDataMessage) message).getAddress().equals(BaseConstants.FRONTEND_ADDRESS)) {
                        CacheEndpoint.updateCacheData(((CacheDataMessage) message).makeCacheData());
                    }
                }
            } catch (InterruptedException e) {
                logger.log(Level.SEVERE, e.getMessage());
                e.printStackTrace();
            }
        });
    }

    public void sendMessage(Message message) {
        logger.info("Sending message: " + message.toString());
        client.send(message);
    }
}
