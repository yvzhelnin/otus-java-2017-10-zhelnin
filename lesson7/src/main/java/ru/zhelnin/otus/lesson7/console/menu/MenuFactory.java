package ru.zhelnin.otus.lesson7.console.menu;

import ru.zhelnin.otus.lesson7.console.menu.abstraction.Menu;
import ru.zhelnin.otus.lesson7.console.menu.impl.DepositMenu;
import ru.zhelnin.otus.lesson7.console.menu.impl.ViewBalanceMenu;
import ru.zhelnin.otus.lesson7.console.menu.impl.WithdrawalMenu;
import ru.zhelnin.otus.lesson7.core.atm.Atm;
import ru.zhelnin.otus.lesson7.core.transaction.abstraction.Transaction;

import java.io.Console;

public class MenuFactory {

    private final Console console;
    private final Atm atm;

    public MenuFactory(Console console, Atm atm) {
        this.console = console;
        this.atm = atm;
    }

    public Menu getMenu(Transaction.Type transactionType) {
        switch (transactionType) {
            case DEPOSIT:
                return new DepositMenu(console, atm);
            case BALANCE_VIEW:
                return new ViewBalanceMenu(console, atm);
            case WITHDRAWAL:
                return new WithdrawalMenu(console, atm);
            case UNKNOWN:
            default:
                return null;
        }
    }
}
