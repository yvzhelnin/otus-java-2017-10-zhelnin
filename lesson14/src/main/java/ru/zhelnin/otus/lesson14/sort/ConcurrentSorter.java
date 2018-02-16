package ru.zhelnin.otus.lesson14.sort;

import ru.zhelnin.otus.lesson14.util.ArrayDivider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SuppressWarnings("WeakerAccess")
public class ConcurrentSorter {

    public static final int THREADS_COUNT = 2;

    private final ArrayDivider divider;
    private final int[] source;


    public ConcurrentSorter(int[] source) {
        this.source = source;
        divider = new ArrayDivider(this.source, THREADS_COUNT);
        divider.divide();
    }

    public int[] sort(boolean withExecutor) throws InterruptedException {
        if (withExecutor) {
            sortWithExecutor();
        } else {
            handleSimpleThreads();
        }

        int[] united = divider.assembleBack();
        Arrays.sort(united);

        return united;
    }

    private void handleSimpleThreads() {
        Iterable<Thread> runThreads = runSortingThreads();
        joinSimpleThreadsThreads(runThreads);
    }

    private void joinSimpleThreadsThreads(Iterable<Thread> threads) {
        threads.forEach(e -> {
            try {
                e.join();
            } catch (InterruptedException ex) {
                System.err.println("Couldn't join a thread " + e.getName());
            }
        });
    }

    private Iterable<Thread> runSortingThreads() {
        int i = 1;
        Collection<Thread> threads = new ArrayList<>();
        for (int[] currentPart : divider.getSubArrays()) {
            threads.add(sortPart(currentPart, i++));
        }

        return threads;
    }

    private Thread sortPart(int[] part, int index) {
        final String threadName = "Sorting-thread-" + index;
        System.err.println("sort started, thread " + threadName);

        return runThread(new Thread(() -> {
            Arrays.sort(part);
            System.err.println("sort done, thread " + threadName);
        }), threadName);
    }

    private Thread runThread(Thread sortThread, String name) {
        sortThread.setName(name);
        sortThread.start();

        return sortThread;
    }

    private void sortWithExecutor() throws InterruptedException {
        sortPartsWithExecutor(Executors.newFixedThreadPool(THREADS_COUNT));
    }

    private void sortPartsWithExecutor(ExecutorService executor) throws InterruptedException {
        for (int[] currentPart : divider.getSubArrays()) {
            executeSortTask(executor, currentPart);
        }
    }

    private static void executeSortTask(ExecutorService executor, int[] part) {
        executor.execute(() -> Arrays.sort(part));
    }
}
