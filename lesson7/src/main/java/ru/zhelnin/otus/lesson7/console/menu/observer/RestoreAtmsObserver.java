package ru.zhelnin.otus.lesson7.console.menu.observer;

import ru.zhelnin.otus.lesson7.console.DepartmentConsoleHandler;

public class RestoreAtmsObserver implements Observer {

    public void notify(Event event, String type) {
        if (type.equals(DepartmentConsoleHandler.RESTORE_ATMS)) {
            event.getDepartment().restoreGroupToInitialStates();
        }
    }
}
