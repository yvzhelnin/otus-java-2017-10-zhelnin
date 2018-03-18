package ru.zhelnin.otus.lesson16.messageserver.server;

import ru.zhelnin.otus.lesson16.core.app.MessageWorker;
import ru.zhelnin.otus.lesson16.core.message.Message;
import ru.zhelnin.otus.lesson16.messageserver.socket.ServerMessageWorker;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

public class MessageServer {

    private static final Logger logger = Logger.getLogger(MessageServer.class.getName());

    private static final int THREADS_NUMBER = 2;
    private static final int PORT = 5050;

    private final ExecutorService executor;
    private final List<MessageWorker> clients;

    public MessageServer() {
        executor = Executors.newFixedThreadPool(THREADS_NUMBER);
        clients = new CopyOnWriteArrayList<>();
    }

    public void start() {
        executor.submit(this::handle);
        executor.submit(() -> {
            try (ServerSocket serverSocket = new ServerSocket(PORT)) {
                logger.info("Server started on port: " + serverSocket.getLocalPort());
                while (!executor.isShutdown()) {
                    Socket socket = serverSocket.accept();
                    MessageWorker client = new ServerMessageWorker(socket);
                    client.init();
                    logger.info("Registering new client");
                    clients.add(client);
                }
            } catch (IOException e) {
                logger.severe("Couldn't start MessageServer");
            }
        });
    }

    @SuppressWarnings("InfiniteLoopStatement")
    private void handle() {
        while (true) {
            for (MessageWorker client : clients) {
                Message message = null;
                try {
                    message = client.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                while (message != null) {
                    logger.info("Handling a message: " + message.toString());
                    sendMessage(message);
                    message = client.pull();
                }
            }
        }
    }

    private void sendMessage(Message message) {
        clients.forEach(e -> e.send(message));
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
