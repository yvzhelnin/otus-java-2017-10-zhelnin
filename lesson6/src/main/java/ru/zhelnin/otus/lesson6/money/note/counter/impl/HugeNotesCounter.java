package ru.zhelnin.otus.lesson6.money.note.counter.impl;

import ru.zhelnin.otus.lesson6.money.note.Denomination;
import ru.zhelnin.otus.lesson6.money.note.Note;
import ru.zhelnin.otus.lesson6.money.note.counter.abstraction.NotesCounter;
import ru.zhelnin.otus.lesson6.money.note.counter.NotesCounterFactory;
import ru.zhelnin.otus.lesson6.money.note.util.DenominationClassifier;
import ru.zhelnin.otus.lesson6.util.exception.NoSuchDenominationException;

import java.util.ArrayList;
import java.util.List;

public class HugeNotesCounter extends NotesCounter {

    public HugeNotesCounter(int amountRequested) {
        super(amountRequested);
    }

    public List<Note> count() throws NoSuchDenominationException {
        List<Note> notes = new ArrayList<>();
        final int hugeNotesAmount = amountRequested / DenominationClassifier.HUGE_DENOMINATION;
        for (int i = 0; i < hugeNotesAmount; i++) {
            notes.add(new Note(Denomination.FIVE_THOUSAND));
        }
        int otherNotesAmount = amountRequested - hugeNotesAmount * DenominationClassifier.HUGE_DENOMINATION;
        if (otherNotesAmount != 0) {
            notes.addAll(NotesCounterFactory.getCounter(otherNotesAmount).count());
        }
        return notes;
    }
}
