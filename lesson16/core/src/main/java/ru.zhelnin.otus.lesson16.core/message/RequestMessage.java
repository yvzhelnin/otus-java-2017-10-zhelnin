package ru.zhelnin.otus.lesson16.core.message;

public class RequestMessage extends Message {

    private final String request;

    public RequestMessage(Class<?> clazz, String request, String address, int backPort, int targetPort) {
        super(clazz, address, backPort, targetPort);
        this.request = request;
    }

    public String getRequest() {
        return request;
    }

    @Override
    public String toString() {
        return "RequestMessage{" +
                "request='" + request + "\', " +
                "direction='" + direction + "\'}";
    }
}
