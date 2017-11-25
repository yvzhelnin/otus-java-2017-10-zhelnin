package ru.zhelnin.otus.lesson6.core.util;

import ru.zhelnin.otus.lesson6.money.note.Denomination;
import ru.zhelnin.otus.lesson6.money.note.Note;
import ru.zhelnin.otus.lesson6.util.exception.NoSuchDenominationException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NotesCounter {

    private static final int MINIMAL_DENOMINATION = Denomination.ONE_HUNDRED.getValue();
    private static final int MIDDLE_DENOMINATION = Denomination.FIVE_HUNDRED.getValue();
    private static final int BIG_DENOMINATION = Denomination.ONE_THOUSAND.getValue();
    private static final int HUGE_DENOMINATION = Denomination.FIVE_THOUSAND.getValue();

    public static List<Note> gatherNotesBeforeWithdrawing(int amountRequested) throws NoSuchDenominationException {
        if (amountRequested < MINIMAL_DENOMINATION || amountRequested % MINIMAL_DENOMINATION != 0) {
            throw new NoSuchDenominationException();
        }
        if (amountRequested < MIDDLE_DENOMINATION) {
            return gatherForSmallAmountRequested(amountRequested);
        } else if (amountRequested < BIG_DENOMINATION) {
            return gatherForMiddleAmountRequested(amountRequested);
        } else if (amountRequested < HUGE_DENOMINATION) {
            return gatherForBigAmountRequested(amountRequested);
        } else if (amountRequested > HUGE_DENOMINATION) {
            return gatherForHugeAmountRequested(amountRequested);
        }
        return Collections.emptyList();
    }

    private static List<Note> gatherForSmallAmountRequested(int amountRequested) throws NoSuchDenominationException {
        List<Note> notes = new ArrayList<>();
        final int notesAmount = amountRequested / MINIMAL_DENOMINATION;
        for (int i = 0; i < notesAmount; i++) {
            notes.add(new Note(Denomination.ONE_HUNDRED));
        }
        return notes;
    }

    private static List<Note> gatherForMiddleAmountRequested(int amountRequested) throws NoSuchDenominationException {
        List<Note> notes = new ArrayList<>();
        notes.add(new Note(Denomination.FIVE_HUNDRED));
        final int smallNotesAmount = (amountRequested - MIDDLE_DENOMINATION) / MINIMAL_DENOMINATION;
        for (int i = 0; i < smallNotesAmount; i++) {
            notes.add(new Note(Denomination.ONE_HUNDRED));
        }
        return notes;
    }

    private static List<Note> gatherForBigAmountRequested(int amountRequested) throws NoSuchDenominationException {
        List<Note> notes = new ArrayList<>();
        final int bigNotesAmount = amountRequested / BIG_DENOMINATION;
        for (int i = 0; i < bigNotesAmount; i++) {
            notes.add(new Note(Denomination.ONE_THOUSAND));
        }
        int otherNotesAmount = amountRequested - bigNotesAmount * BIG_DENOMINATION;
        if (otherNotesAmount != 0) {
            notes.addAll(gatherNotesBeforeWithdrawing(otherNotesAmount));
        }
        return notes;
    }

    private static List<Note> gatherForHugeAmountRequested(int amountRequested) throws NoSuchDenominationException {
        List<Note> notes = new ArrayList<>();
        final int hugeNotesAmount = amountRequested / HUGE_DENOMINATION;
        for (int i = 0; i < hugeNotesAmount; i++) {
            notes.add(new Note(Denomination.FIVE_THOUSAND));
        }
        int otherNotesAmount = amountRequested - hugeNotesAmount * HUGE_DENOMINATION;
        if (otherNotesAmount != 0) {
            notes.addAll(gatherNotesBeforeWithdrawing(otherNotesAmount));
        }
        return notes;
    }
}
