package ru.zhelnin.otus.lesson8.builder.factory.abstraction;

import ru.zhelnin.otus.lesson8.builder.factory.JsonBuilderFactory;
import ru.zhelnin.otus.lesson8.builder.util.BuilderUtil;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

public abstract class JsonBuilder<T> {

    protected static final JsonObjectBuilder builder = Json.createObjectBuilder();

    protected final String name;
    protected final T o;

    public JsonBuilder(T o, String name) {
        this.name = name;
        this.o = o;
    }

    public abstract JsonObject build() throws IllegalAccessException;

    protected void jsonifyCollectionElement(JsonArrayBuilder jsonArray, Object current) throws IllegalAccessException {
        if (BuilderUtil.isPrimitive(current)) {
            jsonArray.add(current.toString());
        } else {
            jsonArray.add(JsonBuilderFactory.getBuilder(current, null).build());
        }
    }
}
