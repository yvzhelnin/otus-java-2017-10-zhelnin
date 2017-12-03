package ru.zhelnin.otus.lesson7.core.transaction;

import ru.zhelnin.otus.lesson7.core.atm.Atm;
import ru.zhelnin.otus.lesson7.core.transaction.abstraction.Transaction;
import ru.zhelnin.otus.lesson7.note.Note;
import ru.zhelnin.otus.lesson7.note.counter.NotesCounterFactory;
import ru.zhelnin.otus.lesson7.note.util.exception.NoSuchDenominationException;

import java.util.Collection;

public class Withdrawal extends Transaction {

    private final Atm targetAtm;

    private final int amountRequested;

    public Withdrawal(int amountRequested, Atm targetAtm) {
        super(Type.WITHDRAWAL);
        this.amountRequested = amountRequested;
        this.targetAtm = targetAtm;
    }

    public Collection<Note> withdraw() throws NoSuchDenominationException {
        Collection<Note> notes = NotesCounterFactory.getCounter(amountRequested).count();
        targetAtm.getAccount().withdraw(amountRequested);
        extractNotes(notes);

        return notes;
    }

    private void extractNotes(Collection<Note> notes) throws NoSuchDenominationException {
        for (Note note : notes) {
            targetAtm.getCassete().getNotes(note.getDenomination());
        }
    }
}
