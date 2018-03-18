package ru.zhelnin.otus.lesson16.dbserver.socket;

import ru.zhelnin.otus.lesson16.core.socket.SocketMessageWorker;

import java.io.IOException;
import java.net.Socket;

public class DBMessageWorker extends SocketMessageWorker {

    private final Socket socket;

    public DBMessageWorker(Socket socket) throws IOException {
        super(socket);
        this.socket = socket;
    }

    public void close() throws IOException {
        super.close();
        socket.close();
    }
}
