package ru.zhelnin.otus.lesson8.builder.factory.impl;

import ru.zhelnin.otus.lesson8.builder.annotations.JsonProperty;
import ru.zhelnin.otus.lesson8.builder.factory.JsonBuilderFactory;
import ru.zhelnin.otus.lesson8.builder.factory.abstraction.JsonBuilder;
import ru.zhelnin.otus.lesson8.builder.util.BuilderUtil;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import java.lang.reflect.Field;

public class ObjectBuilder<T> extends JsonBuilder<T> {


    public ObjectBuilder(T o, String name) {
        super(o, name);
    }

    @Override
    public JsonObject build() throws IllegalAccessException {
        JsonObjectBuilder entryBuilder = Json.createObjectBuilder();
        for (Field field : o.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(JsonProperty.class)) {
                final Object fieldValue = field.get(o);
                if (BuilderUtil.isPrimitive(fieldValue)) {
                    entryBuilder.add(field.getName(), fieldValue.toString());
                } else {
                    entryBuilder.add(field.getName(), JsonBuilderFactory.getBuilder(fieldValue, null).build());
                }
            }
        }
        return name == null ? entryBuilder.build() : builder.add(name, entryBuilder).build();
    }
}
