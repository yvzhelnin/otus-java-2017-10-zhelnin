package ru.zhelnin.otus.lesson7.util;

import java.util.Arrays;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class EnumHelper<T, R> {

    public Set<R> getAvailableValues(Class<T> enumType, Function<T, R> function) {
        return Arrays.stream(enumType.getEnumConstants()).map(function).collect(Collectors.toSet());
    }
}
