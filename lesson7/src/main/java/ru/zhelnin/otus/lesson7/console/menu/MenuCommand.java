package ru.zhelnin.otus.lesson7.console.menu;

import ru.zhelnin.otus.lesson7.console.department.DepartmentConsoleHandler;
import ru.zhelnin.otus.lesson7.console.menu.abstraction.Menu;
import ru.zhelnin.otus.lesson7.console.menu.impl.DepositMenu;
import ru.zhelnin.otus.lesson7.console.menu.impl.ViewBalanceMenu;
import ru.zhelnin.otus.lesson7.console.menu.impl.WithdrawalMenu;
import ru.zhelnin.otus.lesson7.core.atm.Atm;
import ru.zhelnin.otus.lesson7.core.transaction.abstraction.Transaction;
import ru.zhelnin.otus.lesson7.note.util.exception.NoSuchDenominationException;

import java.io.Console;
import java.util.HashMap;
import java.util.Map;

public class MenuCommand {

    private static final Map<Transaction.Type, Menu> menus = new HashMap<>();

    private final Console console;
    private final Atm atm;

    public MenuCommand(Console console, Atm atm) {
        this.console = console;
        this.atm = atm;
        menus.put(Transaction.Type.DEPOSIT, new DepositMenu(console, atm));
        menus.put(Transaction.Type.BALANCE_VIEW, new ViewBalanceMenu(console, atm));
        menus.put(Transaction.Type.WITHDRAWAL, new WithdrawalMenu(console, atm));
    }

    public void execute(Transaction.Type transactionType) throws NoSuchDenominationException {
        Menu menu = menus.get(transactionType);
        if (menu != null) {
            menu.handleMenu();
        } else {
            DepartmentConsoleHandler.execute(console, atm.getDepartment());
        }
    }
}
