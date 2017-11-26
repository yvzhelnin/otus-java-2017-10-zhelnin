package ru.zhelnin.otus.lesson6.console.menu;

import ru.zhelnin.otus.lesson6.console.menu.abstraction.Menu;
import ru.zhelnin.otus.lesson6.console.menu.impl.DepositMenu;
import ru.zhelnin.otus.lesson6.core.transaction.TransactionType;
import ru.zhelnin.otus.lesson6.console.menu.impl.ViewBalanceMenu;
import ru.zhelnin.otus.lesson6.console.menu.impl.WithdrawalMenu;

import java.io.Console;

public class MenuFactory {

    private final Console console;

    public MenuFactory(Console console) {
        this.console = console;
    }

    public Menu getMenu(TransactionType transactionType) {
        switch (transactionType) {
            case DEPOSIT:
                return new DepositMenu(console);
            case BALANCE_VIEW:
                return new ViewBalanceMenu(console);
            case WITHDRAWAL:
                return new WithdrawalMenu(console);
            default:
                return null;
        }
    }
}
