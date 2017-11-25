package ru.zhelnin.otus.lesson6.core.util;

import org.junit.Assert;
import org.junit.Test;
import ru.zhelnin.otus.lesson6.money.note.Denomination;
import ru.zhelnin.otus.lesson6.money.note.Note;
import ru.zhelnin.otus.lesson6.util.exception.NoSuchDenominationException;

import java.util.ArrayList;
import java.util.List;

public class NotesCounterTest {

    @Test(expected = NoSuchDenominationException.class)
    public void gatherNotesBeforeWithdrawingTooSmallValue() throws NoSuchDenominationException {
        NotesCounter.gatherNotesBeforeWithdrawing(50);
    }

    @Test(expected = NoSuchDenominationException.class)
    public void gatherNotesBeforeWithdrawingNotMultipleToOneHundredValue() throws NoSuchDenominationException {
        NotesCounter.gatherNotesBeforeWithdrawing(650);
    }

    @Test
    public void gatherNotesBeforeWithdrawingLessThenFiveHundredValue() throws NoSuchDenominationException {
        List<Note> expectedResult = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            expectedResult.add(new Note(Denomination.ONE_HUNDRED));
        }
        List<Note> actualResult = NotesCounter.gatherNotesBeforeWithdrawing(400);
        Assert.assertEquals(expectedResult.size(), actualResult.size());
        Assert.assertEquals(expectedResult.get(0).getDenomination().getValue(), actualResult.get(0).getDenomination().getValue());
    }

    @Test
    public void gatherNotesBeforeWithdrawingLessOneThousandValue() throws NoSuchDenominationException {
        List<Note> expectedResult = new ArrayList<>();
        expectedResult.add(new Note(Denomination.FIVE_HUNDRED));
        for (int i = 0; i < 4; i++) {
            expectedResult.add(new Note(Denomination.ONE_HUNDRED));
        }
        List<Note> actualResult = NotesCounter.gatherNotesBeforeWithdrawing(900);
        Assert.assertEquals(expectedResult.size(), actualResult.size());
        Assert.assertEquals(expectedResult.get(0).getDenomination().getValue(), actualResult.get(0).getDenomination().getValue());
    }

    @Test
    public void gatherNotesBeforValueEqualsMiddleDenomination() throws NoSuchDenominationException {
        List<Note> actualResult = NotesCounter.gatherNotesBeforeWithdrawing(500);
        Assert.assertEquals(500, actualResult.get(0).getDenomination().getValue());
        Assert.assertEquals(1, actualResult.size());
    }

    @Test
    public void gatherNotesBeforeWithdrawingBigValueMultipleToOneThousand() throws NoSuchDenominationException {
        List<Note> expectedResult = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            expectedResult.add(new Note(Denomination.ONE_THOUSAND));
        }
        List<Note> actualResult = NotesCounter.gatherNotesBeforeWithdrawing(4000);
        Assert.assertEquals(expectedResult.size(), actualResult.size());
        Assert.assertEquals(expectedResult.get(0).getDenomination().getValue(), actualResult.get(0).getDenomination().getValue());
    }

    @Test
    public void gatherNotesBeforeWithdrawingBigValueNotMultipleToOneThousand() throws NoSuchDenominationException {
        List<Note> expectedResult = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            expectedResult.add(new Note(Denomination.ONE_THOUSAND));
        }
        expectedResult.add(new Note(Denomination.FIVE_HUNDRED));
        expectedResult.add(new Note(Denomination.ONE_HUNDRED));
        List<Note> actualResult = NotesCounter.gatherNotesBeforeWithdrawing(4600);
        Assert.assertEquals(expectedResult.size(), actualResult.size());
        Assert.assertEquals(expectedResult.get(0).getDenomination().getValue(), actualResult.get(0).getDenomination().getValue());
    }

    @Test
    public void gatherNotesBeforeWithdrawingHugeValueMultipleToFiveThousand() throws NoSuchDenominationException {
        List<Note> expectedResult = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            expectedResult.add(new Note(Denomination.FIVE_THOUSAND));
        }
        List<Note> actualResult = NotesCounter.gatherNotesBeforeWithdrawing(20000);
        Assert.assertEquals(expectedResult.size(), actualResult.size());
        Assert.assertEquals(expectedResult.get(0).getDenomination().getValue(), actualResult.get(0).getDenomination().getValue());
    }

    @Test
    public void gatherNotesBeforeWithdrawingHugeValueNotMultipleToFiveThousand() throws NoSuchDenominationException {
        List<Note> expectedResult = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            expectedResult.add(new Note(Denomination.FIVE_THOUSAND));
        }
        expectedResult.add(new Note(Denomination.ONE_THOUSAND));
        for (int i = 0; i < 4; i++) {
            expectedResult.add(new Note(Denomination.ONE_HUNDRED));
        }
        List<Note> actualResult = NotesCounter.gatherNotesBeforeWithdrawing(26400);
        Assert.assertEquals(expectedResult.size(), actualResult.size());
        Assert.assertEquals(expectedResult.get(0).getDenomination().getValue(), actualResult.get(0).getDenomination().getValue());
    }
}
