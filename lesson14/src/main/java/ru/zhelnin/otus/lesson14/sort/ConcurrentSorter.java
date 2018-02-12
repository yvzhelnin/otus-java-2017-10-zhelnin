package ru.zhelnin.otus.lesson14.sort;

import ru.zhelnin.otus.lesson14.util.ArrayDivider;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConcurrentSorter {

    public static final int THREADS_COUNT = 4;

    private final ArrayDivider divider;
    private final int[] source;


    public ConcurrentSorter(int[] source) {
        this.source = source;
        divider = new ArrayDivider(this.source);
        divider.divide();
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
        System.err.println("sort start");
        Thread sortThread = new Thread(() -> Arrays.sort(part));
        sortThread.start();
        sortThread.join();
        System.err.println("sort done");
    }

    private void sortWithExecutor() throws InterruptedException {
        sortPartsWithExecutor(Executors.newFixedThreadPool(THREADS_COUNT));
    }

    private void sortPartsWithExecutor(ExecutorService executor) throws InterruptedException {
        executeSortTask(executor, divider.getFirst());
        executeSortTask(executor, divider.getSecond());
        executeSortTask(executor, divider.getThird());
        executeSortTask(executor, divider.getForth());
    }

    private static void executeSortTask(ExecutorService executor, int[] part) {
        executor.execute(() -> Arrays.sort(part));
    }
}
