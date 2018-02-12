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
        int previousLength = 0;
        for (int i = 0; i < subArrays.length; i++) {
            if (i > 0) {
                previousLength += subArrays[i - 1].length;
            }
            divideCurrent(subArrays[i], previousLength);
        }
    }

    private void divideCurrent(int[] target, int startIndex) {
        System.arraycopy(source, startIndex, target, 0, target.length);
    }

    public int[] assembleBack() {
        int[] result = new int[source.length];
        assembleParts(result);

        return result;
    }

    private void assembleParts(int[] result) {
        int previousLength = 0;
        for (int i = 0; i < subArrays.length; i++) {
            if (i > 0) {
                previousLength += subArrays[i - 1].length;
            }
            addCurrent(result, subArrays[i], previousLength);
        }
    }

    private void addCurrent(int[] target, int[] source, int startIndex) {
        System.arraycopy(source, 0, target, startIndex, source.length);
    }

    public int[][] getSubArrays() {
        return subArrays;
    }
}
