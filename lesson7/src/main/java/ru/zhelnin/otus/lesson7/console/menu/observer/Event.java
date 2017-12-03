package ru.zhelnin.otus.lesson7.console.menu.observer;

import ru.zhelnin.otus.lesson7.core.department.AtmDepartment;

public class Event {

    private final AtmDepartment department;

    public Event(AtmDepartment department) {
        this.department = department;
    }

    public AtmDepartment getDepartment() {
        return department;
    }
}
