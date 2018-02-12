package ru.zhelnin.otus.lesson14.sort;

import ru.zhelnin.otus.lesson14.util.ArrayDivider;

import java.util.Arrays;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ConcurrentSorter {

    public static final int THREADS_COUNT = 4;

    private final ArrayDivider divider;
    private final int[] source;


    public ConcurrentSorter(int[] source) {
        this.source = source;
        divider = new ArrayDivider(this.source);
    }

    public int[] getSource() {
        return source;
    }

    public int[] sort(boolean withExecutor) throws InterruptedException {
        if (withExecutor) {
            sortWithExecutor();
        } else {
            sortParts();
        }

        int[] united = divider.assembleBack();
        Arrays.sort(united);

        return united;
    }

    private void sortParts() throws InterruptedException {
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

    private void sortWithExecutor() throws InterruptedException {
        sortPartsWithExecutor(new ThreadPoolExecutor(THREADS_COUNT, THREADS_COUNT, 100L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>()));
    }

    private void sortPartsWithExecutor(ThreadPoolExecutor executor) throws InterruptedException {
        executeSortTask(executor, divider.getFirst());
        executeSortTask(executor, divider.getSecond());
        executeSortTask(executor, divider.getThird());
        executeSortTask(executor, divider.getForth());
    }

    private static void executeSortTask(ThreadPoolExecutor executor, int[] part) {
        executor.execute(new Thread(() -> Arrays.sort(part)));
    }
}
