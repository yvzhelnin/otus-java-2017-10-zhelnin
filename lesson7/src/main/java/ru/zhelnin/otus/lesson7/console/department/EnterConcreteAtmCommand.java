package ru.zhelnin.otus.lesson7.console.department;

import ru.zhelnin.otus.lesson7.core.department.AtmDepartment;
import ru.zhelnin.otus.lesson7.note.util.exception.NoSuchDenominationException;

import java.io.Console;

public class EnterConcreteAtmCommand implements DepartmentConsoleCommand {

    private final AtmDepartment department;
    private final Console console;

    public EnterConcreteAtmCommand(AtmDepartment department, Console console) {
        this.department = department;
        this.console = console;
    }

    @Override
    public void execute() throws NoSuchDenominationException {
        DepartmentConsoleHandler.enterConcreteAtm(console, department);
    }
}
