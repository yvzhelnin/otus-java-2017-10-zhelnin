package ru.zhelnin.otus.lesson16.core.message;

public abstract class Message {

    public static final String CLASS_NAME_VARIABLE = "className";

    private final String className;

    protected Message(Class<?> clazz) {
        this.className = clazz.getName();
    }
}
