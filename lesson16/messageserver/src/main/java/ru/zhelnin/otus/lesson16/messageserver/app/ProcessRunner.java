package ru.zhelnin.otus.lesson16.messageserver.app;

import java.io.IOException;

public interface ProcessRunner {

    void start(String command) throws IOException;

    void stop();

    String getOutput();
}
