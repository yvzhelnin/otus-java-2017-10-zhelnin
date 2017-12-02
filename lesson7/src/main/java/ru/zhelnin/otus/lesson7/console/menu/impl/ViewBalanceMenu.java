package ru.zhelnin.otus.lesson7.console.menu.impl;

import ru.zhelnin.otus.lesson7.console.ConsoleHandler;
import ru.zhelnin.otus.lesson7.console.menu.abstraction.Menu;
import ru.zhelnin.otus.lesson7.core.transaction.BalanceView;
import ru.zhelnin.otus.lesson7.core.transaction.TransactionReceiver;
import ru.zhelnin.otus.lesson7.note.util.exception.NoSuchDenominationException;

import java.io.Console;

public class ViewBalanceMenu extends Menu {

    public ViewBalanceMenu(Console console) {
        super(console);
    }

    public void handleMenu() throws NoSuchDenominationException {
        ConsoleHandler.printBalance(TransactionReceiver.accept(new BalanceView()), console);
    }
}
