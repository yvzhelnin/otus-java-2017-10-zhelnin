package ru.zhelnin.otus.lesson8.builder;

import ru.zhelnin.otus.lesson8.builder.annotations.JsonClass;
import ru.zhelnin.otus.lesson8.builder.factory.JsonBuilderFactory;

public class ObjectReader {

    public static <T> String makeJson(T o) throws IllegalAccessException {
        return JsonBuilderFactory.getBuilder(o, o.getClass().getAnnotation(JsonClass.class).value()).build().toString();
    }

    public static <T> String makeJson(T o, String name) throws IllegalAccessException {
        return JsonBuilderFactory.getBuilder(o, name).build().toString();
    }
}
