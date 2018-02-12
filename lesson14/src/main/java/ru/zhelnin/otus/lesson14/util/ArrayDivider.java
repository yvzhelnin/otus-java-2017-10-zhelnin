package ru.zhelnin.otus.lesson14.util;

public class ArrayDivider {

    private final int[] source;
    private final int count;

    private int[][] subArrays;

    public ArrayDivider(int[] source, int count) {
        this.source = source;
        this.count = count;
        fillSubArrays();
    }

    private void fillSubArrays() {
        subArrays = new int[count][];
        final int subArraySize = source.length / count;
        for (int i = 0; i < count - 1; i++) {
            subArrays[i] = new int[subArraySize];
        }
        subArrays[count - 1] = new int[source.length - subArraySize * (count - 1)];
    }

    public void divide() {
        processParts(this::divideCurrent, source, true);
    }

    private void divideCurrent(int[] target, int[] raw, int startIndex) {
        System.arraycopy(raw, startIndex, target, 0, target.length);
    }

    public int[] assembleBack() {
        int[] result = new int[source.length];
        processParts(this::addCurrent, result, false);

        return result;
    }

    private void addCurrent(int[] target, int[] source, int startIndex) {
        System.arraycopy(source, 0, target, startIndex, source.length);
    }

    private void processParts(DivideAction action, int[] result, boolean isDividing) {
        int previousLength = 0;
        for (int i = 0; i < subArrays.length; i++) {
            if (i > 0) {
                previousLength += subArrays[i - 1].length;
            }
            if (isDividing) {
                action.apply(subArrays[i], result, previousLength);
            } else {
                action.apply(result, subArrays[i], previousLength);
            }
        }
    }

    public int[][] getSubArrays() {
        return subArrays;
    }
}
