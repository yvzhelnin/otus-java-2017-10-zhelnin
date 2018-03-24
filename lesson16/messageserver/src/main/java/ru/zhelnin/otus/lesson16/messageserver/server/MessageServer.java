package ru.zhelnin.otus.lesson16.messageserver.server;

import ru.zhelnin.otus.lesson16.core.app.MessageWorker;
import ru.zhelnin.otus.lesson16.core.message.Message;
import ru.zhelnin.otus.lesson16.core.util.BaseConstants;
import ru.zhelnin.otus.lesson16.messageserver.socket.ServerMessageWorker;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

public class MessageServer {

    private static final Logger logger = Logger.getLogger(MessageServer.class.getName());

    private static final int THREADS_NUMBER = 5;

    private static final String FRONTEND_SERVICE_NAME = "frontend";
    private static final String DB_SERVICE_NAME = "database";

    private final ExecutorService executor;
    private final List<MessageWorker> frontendClients;
    private final List<MessageWorker> dbClients;

    public MessageServer() {
        executor = Executors.newFixedThreadPool(THREADS_NUMBER);
        frontendClients = new CopyOnWriteArrayList<>();
        dbClients = new CopyOnWriteArrayList<>();
    }

    public void start() {
        executor.submit(this::handleFrontend);
        executor.submit(this::handleDb);
        executor.submit(() -> provideConnection(FRONTEND_SERVICE_NAME, BaseConstants.FRONTDEND_SOCKET_PORT, frontendClients));
        executor.submit(() -> provideConnection(DB_SERVICE_NAME, BaseConstants.DB_SOCKET_PORT, dbClients));
    }

    private void provideConnection(String serviceName, int portNumber, Collection<MessageWorker> clients) {
        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
            logger.info(serviceName + " server started on port: " + serverSocket.getLocalPort());
            while (!executor.isShutdown()) {
                Socket socket = serverSocket.accept();
                MessageWorker client = new ServerMessageWorker(socket);
                client.init();
                logger.info("Registering new " + serviceName + " client");
                clients.add(client);
            }
        } catch (IOException e) {
            logger.severe("Couldn't start " + serviceName + " MessageServer");
        }
    }

    @SuppressWarnings("InfiniteLoopStatement")
    private void handleFrontend() {
        while (true) {
            for (MessageWorker client : frontendClients) {
                Message message = null;
                try {
                    message = client.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                while (message != null) {
                    logger.info("Handling a message: " + message.toString());
                    sendMessageToDb(message);
                    message = client.pull();
                }
            }
        }
    }

    @SuppressWarnings("InfiniteLoopStatement")
    private void handleDb() {
        while (true) {
            for (MessageWorker client : dbClients) {
                Message message = null;
                try {
                    message = client.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                while (message != null) {
                    logger.info("Handling a message: " + message.toString());
                    sendMessageToFrontend(message);
                    message = client.pull();
                }
            }
        }
    }

    private void sendMessageToDb(Message message) {
        logger.info("Sending message to DB");
        dbClients.get(new Random().nextInt(dbClients.size())).send(message);
    }

    private void sendMessageToFrontend(Message message) {
        logger.info("Sending message to frontend");
        MessageWorker addressee = frontendClients
                .stream()
                .filter(e -> e.getSocketRemotePort() == message.getTargetPortNumber())
                .findFirst()
                .orElse(null);
        if (addressee != null) {
            addressee.send(message);
        }
    }

    public boolean getRunning() {
        return true;
    }

    public void setRunning(boolean running) {
        if (!running) {
            executor.shutdown();
        }
    }
}
