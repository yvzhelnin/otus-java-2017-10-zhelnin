package ru.zhelnin.otus.lesson14.util;

@FunctionalInterface
public interface DivideAction {

    void apply(int[] target, int[] source, int previousLength);
}
