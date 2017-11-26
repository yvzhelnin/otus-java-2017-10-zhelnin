package ru.zhelnin.otus.lesson6.util;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class EnumHelper<T, R> {

    public List<R> getAvailableValues(Class<T> enumType, Function<T, R> function) {
        return Arrays.stream(enumType.getEnumConstants()).map(function).collect(Collectors.toList());
    }
}
