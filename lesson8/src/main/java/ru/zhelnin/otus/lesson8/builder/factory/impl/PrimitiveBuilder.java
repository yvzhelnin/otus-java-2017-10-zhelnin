package ru.zhelnin.otus.lesson8.builder.factory.impl;

import ru.zhelnin.otus.lesson8.builder.factory.abstraction.JsonBuilder;

import javax.json.JsonObject;

public class PrimitiveBuilder<T> extends JsonBuilder<T> {

    public PrimitiveBuilder(T o, String name) {
        super(o, name);
    }

    @Override
    public JsonObject build() {
        return builder.add(name, o.toString()).build();
    }
}
