package ru.zhelnin.otus.lesson16.core.app;

import ru.zhelnin.otus.lesson16.core.message.Message;

import java.io.IOException;

public interface MessageWorker {

    void init();

    void send(Message message);

    Message pull();

    Message take() throws InterruptedException;

    void close() throws IOException;

    String getAddress();
}
