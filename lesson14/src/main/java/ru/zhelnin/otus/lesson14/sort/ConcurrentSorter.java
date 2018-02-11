package ru.zhelnin.otus.lesson14.sort;

import ru.zhelnin.otus.lesson14.util.ArrayDivider;
import ru.zhelnin.otus.lesson14.util.RandomArrayBuilder;

import java.util.Arrays;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ConcurrentSorter {

    public static final int THREADS_COUNT = 4;

    private static final int[] source = RandomArrayBuilder.generate();
    private static final ArrayDivider divider = new ArrayDivider(source);

    static {
        divider.divide();
    }

    public static int[] getSource() {
        return source;
    }

    public static int[] sort(boolean withExecutor) throws InterruptedException {
        if (withExecutor) {
            sortWithExecutor();
        } else {
            sortParts();
        }

        int[] united = divider.assembleBack();
        Arrays.sort(united);

        return united;
    }

    private static void sortParts() throws InterruptedException {
        sortPart(divider.getFirst());
        sortPart(divider.getSecond());
        sortPart(divider.getThird());
        sortPart(divider.getForth());
    }

    private static void sortPart(int[] part) throws InterruptedException {
        Thread sortThread = new Thread(() -> Arrays.sort(part));
        sortThread.start();
        sortThread.join();
    }

    private static void sortWithExecutor() throws InterruptedException {
        sortPartsWithExecutor(new ThreadPoolExecutor(THREADS_COUNT, THREADS_COUNT, 100L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>()));
    }

    private static void sortPartsWithExecutor(ThreadPoolExecutor executor) throws InterruptedException {
        executeSortTask(executor, divider.getFirst());
        executeSortTask(executor, divider.getSecond());
        executeSortTask(executor, divider.getThird());
        executeSortTask(executor, divider.getForth());
    }

    private static void executeSortTask(ThreadPoolExecutor executor, int[] part) {
        executor.execute(new Thread(() -> Arrays.sort(part)));
    }
}
