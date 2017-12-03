package ru.zhelnin.otus.lesson7.console.menu.observer;

@FunctionalInterface
public interface Observer {

    void notify(Event event, String type);
}
