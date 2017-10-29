package ru.zhelnin.otus.lesson3.main;

import ru.zhelnin.otus.lesson3.collection.CustomList;
import ru.zhelnin.otus.lesson3.util.StringConstant;

import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        addAllElementsToCustomListDemo();
        copyCustomListDemo();
        sortCollectionsDemo();
    }

    private static void addAllElementsToCustomListDemo() {
        System.out.println("Add all elements demo:\n");
        List<String> source = fillSource();

        Collections.addAll(source, StringConstant.FORTH, StringConstant.FIFTH, StringConstant.SIXTH, StringConstant.SEVENTH);
        printList(source);
        printDelimiter();
    }

    private static void copyCustomListDemo() {
        List<String> source = fillSource();

        List<Object> dest = new CustomList<>();
        dest.add(StringConstant.PLACEHOLDER);
        dest.add(StringConstant.PLACEHOLDER);
        dest.add(StringConstant.PLACEHOLDER);
        Collections.copy(dest, source);

        System.out.println("Original list:\n");
        printList(source);

        System.out.println("\nCopied list:\n");
        printList(dest);
        printDelimiter();
    }

    private static void sortCollectionsDemo() {
        List<String> source = fillSource();

        System.out.println("Original list:\n");
        printList(source);

        Collections.sort(source, String::compareToIgnoreCase);
        System.out.println("\nSorted list:\n");
        printList(source);
        printDelimiter();
    }

    private static List<String> fillSource() {
        List<String> source = new CustomList<>();
        source.add(StringConstant.THIRD);
        source.add(StringConstant.SECOND);
        source.add(StringConstant.FIRST);

        return source;
    }

    private static void printDelimiter() {
        System.out.println("============================================");
    }

    private static void printList(List source) {
        for (Object element : source) {
            System.out.println(element);
        }
    }
}
