package ru.zhelnin.otus.lesson7.note.counter.impl;

import ru.zhelnin.otus.lesson7.note.Denomination;
import ru.zhelnin.otus.lesson7.note.Note;
import ru.zhelnin.otus.lesson7.note.counter.abstraction.NotesCounter;
import ru.zhelnin.otus.lesson7.note.util.DenominationClassifier;

import java.util.ArrayList;
import java.util.List;

public class SmallNotesCounter extends NotesCounter {

    public SmallNotesCounter(int amountRequested) {
        super(amountRequested);
    }

    public List<Note> count() {
        List<Note> notes = new ArrayList<>();
        final int notesAmount = amountRequested / DenominationClassifier.MINIMAL_DENOMINATION;
        for (int i = 0; i < notesAmount; i++) {
            notes.add(new Note(Denomination.ONE_HUNDRED));
        }
        return notes;
    }
}
