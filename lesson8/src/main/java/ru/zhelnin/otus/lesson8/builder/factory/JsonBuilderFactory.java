package ru.zhelnin.otus.lesson8.builder.factory;

import ru.zhelnin.otus.lesson8.builder.factory.abstraction.JsonBuilder;
import ru.zhelnin.otus.lesson8.builder.factory.impl.ArrayBuilder;
import ru.zhelnin.otus.lesson8.builder.factory.impl.CollectionBuilder;
import ru.zhelnin.otus.lesson8.builder.factory.impl.ObjectBuilder;
import ru.zhelnin.otus.lesson8.builder.factory.impl.PrimitiveBuilder;
import ru.zhelnin.otus.lesson8.builder.util.BuilderUtil;

public class JsonBuilderFactory {

    public static <T> JsonBuilder getBuilder(T o, String name) throws IllegalAccessException {
        if (BuilderUtil.isPrimitive(o)) {
            return new PrimitiveBuilder<>(o, name);
        } else if (o.getClass().isArray()) {
            return new ArrayBuilder<>(o, name);
        } else if (o instanceof Iterable) {
            return new CollectionBuilder<>(o, name);
        } else {
            return new ObjectBuilder<>(o, name);
        }
    }
}
