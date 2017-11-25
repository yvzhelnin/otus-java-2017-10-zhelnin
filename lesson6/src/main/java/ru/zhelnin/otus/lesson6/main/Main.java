package ru.zhelnin.otus.lesson6.main;

import java.io.Console;

public class Main {

    private static final String WELCOME_MESSAGE = "Welcome to Zhelnin Finance Inc. Atm!\n" +
            "Choose an action you want to execute:\n" +
            "1 - deposit money;\n" +
            "2 - withdraw money\n" +
            "3 - view balance";

    public static void main(String[] args) {
        Console console = System.console();
        if (console != null) {
            System.out.println(WELCOME_MESSAGE);
            console.format("\nPress ENTER to proceed.\n");
            String selected = console.readLine();
            System.out.println(selected);
        }
    }
}
