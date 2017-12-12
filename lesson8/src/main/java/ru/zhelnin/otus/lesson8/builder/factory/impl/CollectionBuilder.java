package ru.zhelnin.otus.lesson8.builder.factory.impl;

import ru.zhelnin.otus.lesson8.builder.factory.abstraction.JsonBuilder;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;

public class CollectionBuilder<T> extends JsonBuilder<T> {

    public CollectionBuilder(T o, String name) {
        super(o, name);
    }

    @Override
    public JsonObject build() throws IllegalAccessException {
        JsonArrayBuilder jsonArray = Json.createArrayBuilder();
        for (Object current : (Iterable) o) {
            jsonifyCollectionElement(jsonArray, current);
        }
        return builder.add(name, jsonArray).build();
    }
}
