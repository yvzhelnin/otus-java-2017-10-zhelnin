package ru.zhelnin.otus.lesson16.dbserver.socket;

import ru.zhelnin.otus.lesson16.core.socket.SocketMessageWorker;

import java.io.IOException;
import java.net.Socket;

public class DBClientMessageWorker extends SocketMessageWorker {

    private final Socket socket;

    DBClientMessageWorker(String host, int port) throws IOException {
        this(new Socket(host, port));
    }

    private DBClientMessageWorker(Socket socket) throws IOException {
        super(socket);
        this.socket = socket;
    }

    public void close() throws IOException {
        super.close();
        socket.close();
    }
}
