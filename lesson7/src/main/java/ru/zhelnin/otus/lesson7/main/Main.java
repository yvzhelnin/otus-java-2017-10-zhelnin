package ru.zhelnin.otus.lesson7.main;

import ru.zhelnin.otus.lesson7.console.DepartmentConsoleHandler;
import ru.zhelnin.otus.lesson7.core.department.AtmDepartment;
import ru.zhelnin.otus.lesson7.note.util.exception.NoSuchDenominationException;

public class Main {

    public static void main(String[] args) throws NoSuchDenominationException {
        DepartmentConsoleHandler.execute(System.console(), new AtmDepartment());
    }
}
