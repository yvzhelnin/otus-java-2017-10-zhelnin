package ru.zhelnin.otus.lesson7.console.util;

import ru.zhelnin.otus.lesson7.console.ConsoleConstants;
import ru.zhelnin.otus.lesson7.console.department.ConsoleHandler;

import java.io.Console;

public class ConsoleParser {

    public static int parse(Console console, String menu, String instruction) {
        try {
            return Integer.valueOf(ConsoleHandler.handle(console, menu, instruction));
        } catch (NumberFormatException e) {
            System.out.println(ConsoleConstants.WRONG_DATA);

            return 0;
        }
    }
}
