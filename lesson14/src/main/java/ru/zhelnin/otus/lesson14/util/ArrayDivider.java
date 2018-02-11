package ru.zhelnin.otus.lesson14.util;

import ru.zhelnin.otus.lesson14.sort.ConcurrentSorter;

public class ArrayDivider {

    private final int[] source;

    private final int[] first;
    private final int[] second;
    private final int[] third;
    private final int[] forth;

    public ArrayDivider(int[] source) {
        this.source = source;
        final int standardSubArraySize = source.length / ConcurrentSorter.THREADS_COUNT;
        first = new int[standardSubArraySize];
        second = new int[standardSubArraySize];
        third = new int[standardSubArraySize];
        forth = new int[source.length - standardSubArraySize * 3];
    }

    public void divide() {
        int index = 0;
        divideCurrent(first, index);
        divideCurrent(second, index += first.length);
        divideCurrent(third, index += second.length);
        divideCurrent(forth, index += third.length);
    }

    private void divideCurrent(int[] target, int startIndex) {
        System.arraycopy(source, startIndex, target, 0, target.length);
    }

    public int[] assembleBack() {
        int[] result = new int[source.length];
        processParts(result);

        return result;
    }

    private void processParts(int[] result) {
        int index = 0;
        addCurrent(first, result, index);
        addCurrent(second, result, index += first.length);
        addCurrent(third, result, index += second.length);
        addCurrent(forth, result, index += third.length);
    }

    private void addCurrent(int[] source, int[] target, int startIndex) {
        System.arraycopy(source, 0, target, startIndex, source.length);
    }

    public int[] getFirst() {
        return first;
    }

    public int[] getSecond() {
        return second;
    }

    public int[] getThird() {
        return third;
    }

    public int[] getForth() {
        return forth;
    }
}
