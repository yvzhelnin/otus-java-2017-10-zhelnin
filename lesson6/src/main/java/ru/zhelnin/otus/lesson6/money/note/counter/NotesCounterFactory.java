package ru.zhelnin.otus.lesson6.money.note.counter;

import ru.zhelnin.otus.lesson6.money.note.counter.abstraction.NotesCounter;
import ru.zhelnin.otus.lesson6.money.note.counter.impl.BigNotesCounter;
import ru.zhelnin.otus.lesson6.money.note.counter.impl.HugeNotesCounter;
import ru.zhelnin.otus.lesson6.money.note.counter.impl.MiddleNotesCounter;
import ru.zhelnin.otus.lesson6.money.note.counter.impl.SmallNotesCounter;
import ru.zhelnin.otus.lesson6.money.note.util.DenominationClassifier;
import ru.zhelnin.otus.lesson6.util.exception.NoSuchDenominationException;

public class NotesCounterFactory {

    public static NotesCounter getCounter(int amountRequested) throws NoSuchDenominationException {
        if (amountRequested < DenominationClassifier.MINIMAL_DENOMINATION || amountRequested % DenominationClassifier.MINIMAL_DENOMINATION != 0) {
            throw new NoSuchDenominationException();
        }
        if (amountRequested < DenominationClassifier.MIDDLE_DENOMINATION) {
            return new SmallNotesCounter(amountRequested);
        } else if (amountRequested < DenominationClassifier.BIG_DENOMINATION) {
            return new MiddleNotesCounter(amountRequested);
        } else if (amountRequested < DenominationClassifier.HUGE_DENOMINATION) {
            return new BigNotesCounter(amountRequested);
        } else {
            return new HugeNotesCounter(amountRequested);
        }
    }
}
