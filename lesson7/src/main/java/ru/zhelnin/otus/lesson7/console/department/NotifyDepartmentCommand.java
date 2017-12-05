package ru.zhelnin.otus.lesson7.console.department;

import ru.zhelnin.otus.lesson7.console.menu.observer.Event;
import ru.zhelnin.otus.lesson7.core.department.AtmDepartment;
import ru.zhelnin.otus.lesson7.note.util.exception.NoSuchDenominationException;

import java.io.Console;

public class NotifyDepartmentCommand implements DepartmentConsoleCommand {

    private final AtmDepartment department;
    private final Console console;
    private final String type;

    public NotifyDepartmentCommand(AtmDepartment department, Console console, String type) {
        this.department = department;
        this.console = console;
        this.type = type;
    }

    @Override
    public void execute() throws NoSuchDenominationException {
        DepartmentConsoleHandler.notify(new Event(department), type);
        DepartmentConsoleHandler.execute(console, department);
    }
}
