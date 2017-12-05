package ru.zhelnin.otus.lesson7.console.department;

import ru.zhelnin.otus.lesson7.note.util.exception.NoSuchDenominationException;

public interface DepartmentConsoleCommand {

    void execute() throws NoSuchDenominationException;
}
