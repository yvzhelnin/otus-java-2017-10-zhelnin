package ru.zhelnin.otus.lesson6.core.transaction.impl;

import ru.zhelnin.otus.lesson6.core.transaction.AtmTransaction;
import ru.zhelnin.otus.lesson6.money.note.Note;

public class Deposit extends AtmTransaction {

    private final Iterable<Note> notes;

    public Deposit(Iterable<Note> notes) {
        this.notes = notes;
    }

    public int execute() {
        int depositingValue = 0;
        for (Note note : notes) {
            ATM.getCassete().insertNote(note);
            depositingValue += note.getDenomination().getValue();
        }
        return ATM.getAccount().withdraw(depositingValue);
    }
}
