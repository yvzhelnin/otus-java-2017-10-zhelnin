package ru.zhelnin.otus.lesson16.core.message;

public abstract class Message {

    public static final String CLASS_NAME_VARIABLE = "className";

    private final String className;

    private String address;

    public <T> Message(Class<T> clazz, String address) {
        this.className = clazz.getName();
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
