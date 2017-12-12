package ru.zhelnin.otus.lesson8.builder.factory.impl;

import ru.zhelnin.otus.lesson8.builder.factory.abstraction.JsonBuilder;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import java.lang.reflect.Array;

public class ArrayBuilder<T> extends JsonBuilder<T> {

    public ArrayBuilder(T o, String name) {
        super(o, name);
    }

    @Override
    public JsonObject build() throws IllegalAccessException {
        JsonArrayBuilder jsonArray = Json.createArrayBuilder();
        for (int i = 0; i < Array.getLength(o); i++) {
            jsonifyCollectionElement(jsonArray, Array.get(o, i));
        }
        return builder.add(name, jsonArray).build();
    }
}
