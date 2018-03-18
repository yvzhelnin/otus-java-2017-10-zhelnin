package ru.zhelnin.otus.lesson16.frontend.socket;

import ru.zhelnin.otus.lesson16.core.socket.SocketMessageWorker;
import ru.zhelnin.otus.lesson16.core.util.BaseConstants;

import java.io.IOException;
import java.net.Socket;

public class FrontendMessageWorker extends SocketMessageWorker {

    private final Socket socket;

    public FrontendMessageWorker(Socket socket) throws IOException {
        super(socket, BaseConstants.FRONTEND_ADDRESS);
        this.socket = socket;
    }

    public void close() throws IOException {
        super.close();
        socket.close();
    }
}
