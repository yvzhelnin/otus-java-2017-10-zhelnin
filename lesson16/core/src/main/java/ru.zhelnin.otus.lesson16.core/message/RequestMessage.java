package ru.zhelnin.otus.lesson16.core.message;

public class RequestMessage extends Message {

    private final String request;

    public RequestMessage(Class<?> clazz, String request, String address) {
        super(clazz, address);
        this.request = request;
    }

    public String getRequest() {
        return request;
    }

    @Override
    public String toString() {
        return "RequestMessage{" +
                "request='" + request + '\'' +
                '}';
    }
}
