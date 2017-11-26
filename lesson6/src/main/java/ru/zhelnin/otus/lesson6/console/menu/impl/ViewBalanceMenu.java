package ru.zhelnin.otus.lesson6.console.menu.impl;

import ru.zhelnin.otus.lesson6.console.ConsoleHandler;
import ru.zhelnin.otus.lesson6.console.menu.abstraction.Menu;
import ru.zhelnin.otus.lesson6.core.transaction.BalanceView;

import java.io.Console;

public class ViewBalanceMenu extends Menu {

    public ViewBalanceMenu(Console console) {
        super(console);
    }

    public void handleMenu() {
        ConsoleHandler.printBalance(BalanceView.view(), console);
    }
}
