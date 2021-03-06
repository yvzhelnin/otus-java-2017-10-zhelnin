package ru.zhelnin.otus.lesson7.note.counter.abstraction;

import ru.zhelnin.otus.lesson7.note.Note;
import ru.zhelnin.otus.lesson7.note.util.exception.NoSuchDenominationException;

import java.util.List;

public abstract class NotesCounter {

    protected final int amountRequested;

    protected NotesCounter(int amountRequested) {
        this.amountRequested = amountRequested;
    }

    public abstract List<Note> count() throws NoSuchDenominationException;
}
