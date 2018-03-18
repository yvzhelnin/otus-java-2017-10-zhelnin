package ru.zhelnin.otus.lesson16.messageserver.socket;

import ru.zhelnin.otus.lesson16.core.socket.SocketMessageWorker;

import java.io.IOException;
import java.net.Socket;

public class ServerMessageWorker extends SocketMessageWorker {

    private final Socket socket;

    public ServerMessageWorker(Socket socket) throws IOException {
        super(socket, "");
        this.socket = socket;
    }

    public void close() throws IOException {
        super.close();
        socket.close();
    }
}
