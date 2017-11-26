package ru.zhelnin.otus.lesson6.note.counter.impl;

import ru.zhelnin.otus.lesson6.note.Denomination;
import ru.zhelnin.otus.lesson6.note.Note;
import ru.zhelnin.otus.lesson6.note.counter.abstraction.NotesCounter;
import ru.zhelnin.otus.lesson6.note.util.DenominationClassifier;

import java.util.ArrayList;
import java.util.List;

public class MiddleNotesCounter extends NotesCounter {

    public MiddleNotesCounter(int amountRequested) {
        super(amountRequested);
    }

    public List<Note> count() {
        List<Note> notes = new ArrayList<>();
        notes.add(new Note(Denomination.FIVE_HUNDRED));
        final int smallNotesAmount = (amountRequested - DenominationClassifier.MIDDLE_DENOMINATION) / DenominationClassifier.MINIMAL_DENOMINATION;
        for (int i = 0; i < smallNotesAmount; i++) {
            notes.add(new Note(Denomination.ONE_HUNDRED));
        }
        return notes;
    }
}
