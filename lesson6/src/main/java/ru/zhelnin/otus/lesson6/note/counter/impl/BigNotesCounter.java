package ru.zhelnin.otus.lesson6.note.counter.impl;

import ru.zhelnin.otus.lesson6.note.Denomination;
import ru.zhelnin.otus.lesson6.note.Note;
import ru.zhelnin.otus.lesson6.note.counter.abstraction.NotesCounter;
import ru.zhelnin.otus.lesson6.note.util.UniversalCounter;
import ru.zhelnin.otus.lesson6.note.util.exception.NoSuchDenominationException;

import java.util.List;

public class BigNotesCounter extends NotesCounter {

    public BigNotesCounter(int amountRequested) {
        super(amountRequested);
    }

    public List<Note> count() throws NoSuchDenominationException {
        return UniversalCounter.count(amountRequested, Denomination.ONE_THOUSAND);
    }
}
