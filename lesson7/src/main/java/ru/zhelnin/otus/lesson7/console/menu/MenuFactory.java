package ru.zhelnin.otus.lesson7.console.menu;

import ru.zhelnin.otus.lesson7.console.menu.abstraction.Menu;
import ru.zhelnin.otus.lesson7.console.menu.impl.DepositMenu;
import ru.zhelnin.otus.lesson7.console.menu.impl.ViewBalanceMenu;
import ru.zhelnin.otus.lesson7.console.menu.impl.WithdrawalMenu;
import ru.zhelnin.otus.lesson7.core.transaction.abstraction.Transaction;

import java.io.Console;

public class MenuFactory {

    private final Console console;

    public MenuFactory(Console console) {
        this.console = console;
    }

    public Menu getMenu(Transaction.Type transactionType) {
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
