package ru.zhelnin.otus.lesson7.console.menu.impl;

import ru.zhelnin.otus.lesson7.console.ConsoleConstants;
import ru.zhelnin.otus.lesson7.console.ConsoleHandler;
import ru.zhelnin.otus.lesson7.console.menu.abstraction.Menu;
import ru.zhelnin.otus.lesson7.console.util.ConsoleParser;
import ru.zhelnin.otus.lesson7.core.transaction.TransactionReceiver;
import ru.zhelnin.otus.lesson7.core.transaction.Withdrawal;
import ru.zhelnin.otus.lesson7.note.Note;
import ru.zhelnin.otus.lesson7.note.util.exception.NoSuchDenominationException;

import java.io.Console;
import java.util.Collection;

public class WithdrawalMenu extends Menu {

    public WithdrawalMenu(Console console) {
        super(console);
    }

    public void handleMenu() throws NoSuchDenominationException {
        try {
            ejectNotes(TransactionReceiver.accept(new Withdrawal(ConsoleParser.parse(console, ConsoleConstants.EMPTY_STRING, ConsoleConstants.WITHDRAWAL_INSTRUCTION))));
        } catch (NoSuchDenominationException e) {
            System.out.println(ConsoleConstants.NO_SUCH_DENOMINATION);
            ConsoleHandler.execute(console);
        }
        ConsoleHandler.execute(console);
    }

    private void ejectNotes(Collection<Note> notes) {
        System.out.println(ConsoleConstants.WITHDRAWAL_SUCCESS);
        notes.stream().map(Note::getDenomination).forEach(System.out::println);
    }
}
