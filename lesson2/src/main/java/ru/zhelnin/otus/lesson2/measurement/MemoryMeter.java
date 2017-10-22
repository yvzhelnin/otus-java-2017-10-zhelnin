package ru.zhelnin.otus.lesson2.measurement;

public class MemoryMeter {

    private static final Runtime runtime = Runtime.getRuntime();

    public static long measureMemory() {
        return runtime.totalMemory() - runtime.freeMemory();
    }

    public static long countStructureSize(long commonSize, long structuresQuantity) {
        return commonSize / structuresQuantity;
    }

    public static void cleanMemory() {
        System.gc();
    }
}
