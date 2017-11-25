package ru.zhelnin.otus.lesson6.money.note.counter.util;

import ru.zhelnin.otus.lesson6.money.note.Denomination;
import ru.zhelnin.otus.lesson6.money.note.Note;
import ru.zhelnin.otus.lesson6.money.note.counter.NotesCounterFactory;
import ru.zhelnin.otus.lesson6.money.note.util.DenominationClassifier;
import ru.zhelnin.otus.lesson6.util.exception.NoSuchDenominationException;

import java.util.ArrayList;
import java.util.List;

public class UniversalCounter {

    public static List<Note> count(int amountRequested, Denomination denomination) throws NoSuchDenominationException {
        List<Note> notes = new ArrayList<>();
        final int bigNotesAmount = amountRequested / denomination.getValue();
        for (int i = 0; i < bigNotesAmount; i++) {
            notes.add(new Note(denomination));
        }
        int otherNotesAmount = amountRequested - bigNotesAmount * denomination.getValue();
        if (otherNotesAmount != 0) {
            notes.addAll(NotesCounterFactory.getCounter(otherNotesAmount).count());
        }
        return notes;
    }
}
