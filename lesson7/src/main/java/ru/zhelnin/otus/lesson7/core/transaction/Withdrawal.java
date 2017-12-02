package ru.zhelnin.otus.lesson7.core.transaction;

import ru.zhelnin.otus.lesson7.core.atm.Atm;
import ru.zhelnin.otus.lesson7.core.transaction.abstraction.Transaction;
import ru.zhelnin.otus.lesson7.note.Note;
import ru.zhelnin.otus.lesson7.note.counter.NotesCounterFactory;
import ru.zhelnin.otus.lesson7.note.util.exception.NoSuchDenominationException;

import java.util.Collection;

public class Withdrawal extends Transaction {

    private static final Atm ATM = Atm.getInstance();

    private final int amountRequested;

    public Withdrawal(int amountRequested) {
        super(Type.WITHDRAWAL);
        this.amountRequested = amountRequested;
    }

    public Collection<Note> withdraw() throws NoSuchDenominationException {
        Collection<Note> notes = NotesCounterFactory.getCounter(amountRequested).count();
        ATM.getAccount().withdraw(amountRequested);

        return notes;
    }
}
