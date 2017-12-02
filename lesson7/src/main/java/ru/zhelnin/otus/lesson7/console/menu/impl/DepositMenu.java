package ru.zhelnin.otus.lesson7.console.menu.impl;

import ru.zhelnin.otus.lesson7.console.ConsoleConstants;
import ru.zhelnin.otus.lesson7.console.ConsoleHandler;
import ru.zhelnin.otus.lesson7.console.menu.abstraction.Menu;
import ru.zhelnin.otus.lesson7.console.util.ConsoleParser;
import ru.zhelnin.otus.lesson7.core.transaction.Deposit;
import ru.zhelnin.otus.lesson7.core.transaction.TransactionReceiver;
import ru.zhelnin.otus.lesson7.note.util.exception.NoSuchDenominationException;

import java.io.Console;

public class DepositMenu extends Menu {

    public DepositMenu(Console console) {
        super(console);
    }

    public void handleMenu() throws NoSuchDenominationException {
        Integer selected = ConsoleParser.parse(console, ConsoleConstants.DEPOSIT_MENU, ConsoleConstants.DEPOSIT_DENOMINATION_INSTRUCTION);
        if (!AVAILABLE_DENOMINATIONS.contains(selected)) {
            ConsoleHandler.handle(console, ConsoleConstants.WELCOME_MENU, ConsoleConstants.WELCOME_INSTRUCTION);
        } else {
            Integer notesAmount = ConsoleParser.parse(console, ConsoleConstants.EMPTY_STRING, ConsoleConstants.DEPOSIT_NOTES_INSTRUCTION);
            ConsoleHandler.printBalance(TransactionReceiver.accept(new Deposit(notesAmount * selected)), console);
        }
    }
}
