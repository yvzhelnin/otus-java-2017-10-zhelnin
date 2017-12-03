package ru.zhelnin.otus.lesson7.console.menu.observer;

import ru.zhelnin.otus.lesson7.console.DepartmentConsoleHandler;

public class PrintNotesAmountObserver implements Observer {

    public void notify(Event event, String type) {
        if (type.equals(DepartmentConsoleHandler.PRINT_NOTES)) {
            event.getDepartment().printNotesAmount();
        }
    }
}
