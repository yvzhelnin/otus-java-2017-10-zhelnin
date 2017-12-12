package ru.zhelnin.otus.lesson8.builder.util;

public class BuilderUtil {

    public static <T> boolean isPrimitive(T o) {
        return o instanceof String || o instanceof Number || o instanceof Boolean;
    }
}
