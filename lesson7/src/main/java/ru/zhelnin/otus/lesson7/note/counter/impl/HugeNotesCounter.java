package ru.zhelnin.otus.lesson7.note.counter.impl;

import ru.zhelnin.otus.lesson7.note.Denomination;
import ru.zhelnin.otus.lesson7.note.Note;
import ru.zhelnin.otus.lesson7.note.counter.abstraction.NotesCounter;
import ru.zhelnin.otus.lesson7.note.util.UniversalCounter;
import ru.zhelnin.otus.lesson7.note.util.exception.NoSuchDenominationException;

import java.util.List;

public class HugeNotesCounter extends NotesCounter {

    public HugeNotesCounter(int amountRequested) {
        super(amountRequested);
    }

    public List<Note> count() throws NoSuchDenominationException {
        return UniversalCounter.count(amountRequested, Denomination.FIVE_THOUSAND);
    }
}
