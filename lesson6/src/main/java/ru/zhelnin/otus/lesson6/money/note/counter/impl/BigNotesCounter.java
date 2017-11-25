package ru.zhelnin.otus.lesson6.money.note.counter.impl;

import ru.zhelnin.otus.lesson6.money.note.Denomination;
import ru.zhelnin.otus.lesson6.money.note.Note;
import ru.zhelnin.otus.lesson6.money.note.counter.abstraction.NotesCounter;
import ru.zhelnin.otus.lesson6.money.note.counter.util.UniversalCounter;
import ru.zhelnin.otus.lesson6.util.exception.NoSuchDenominationException;

import java.util.List;

public class BigNotesCounter extends NotesCounter {

    public BigNotesCounter(int amountRequested) {
        super(amountRequested);
    }

    public List<Note> count() throws NoSuchDenominationException {
        return UniversalCounter.count(amountRequested, Denomination.ONE_THOUSAND);
    }
}
