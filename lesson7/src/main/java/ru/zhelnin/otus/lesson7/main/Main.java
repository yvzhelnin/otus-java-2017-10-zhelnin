package ru.zhelnin.otus.lesson7.main;

import ru.zhelnin.otus.lesson7.console.DepartmentConsoleHandler;
import ru.zhelnin.otus.lesson7.console.menu.observer.PrintNotesAmountObserver;
import ru.zhelnin.otus.lesson7.console.menu.observer.RestoreAtmsObserver;
import ru.zhelnin.otus.lesson7.core.department.AtmDepartment;
import ru.zhelnin.otus.lesson7.note.util.exception.NoSuchDenominationException;

public class Main {

    public static void main(String[] args) throws NoSuchDenominationException {
        DepartmentConsoleHandler.register(new PrintNotesAmountObserver());
        DepartmentConsoleHandler.register(new RestoreAtmsObserver());
        DepartmentConsoleHandler.execute(System.console(), new AtmDepartment());
    }
}
