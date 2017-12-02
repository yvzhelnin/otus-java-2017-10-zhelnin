package ru.zhelnin.otus.lesson7.main;

import ru.zhelnin.otus.lesson7.console.ConsoleHandler;
import ru.zhelnin.otus.lesson7.note.util.exception.NoSuchDenominationException;

public class Main {

    public static void main(String[] args) throws NoSuchDenominationException {
        ConsoleHandler.execute(System.console());
    }
}
