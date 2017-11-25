package ru.zhelnin.otus.lesson6.core.atm;

import ru.zhelnin.otus.lesson6.money.note.Denomination;
import ru.zhelnin.otus.lesson6.money.note.Note;

public class Cassete {

    private static final int INITIAL_ONE_HUNDRED_NOTES_QUANTITY = 1000;
    private static final int INITIAL_FIVE_HUNDRED_NOTES_QUANTITY = 500;
    private static final int INITIAL_ONE_THOUSAND_NOTES_QUANTITY = 100;
    private static final int INITIAL_FIVE_THOUSAND_NOTES_QUANTITY = 50;

    private int oneHundredNotesQuantity;
    private int fiveHundredNotesQuantity;
    private int oneThousandNotesQuantity;
    private int fiveThousandNotesQuantity;

    public Cassete() {
        this.oneHundredNotesQuantity = INITIAL_ONE_HUNDRED_NOTES_QUANTITY;
        this.fiveHundredNotesQuantity = INITIAL_FIVE_HUNDRED_NOTES_QUANTITY;
        this.oneThousandNotesQuantity = INITIAL_ONE_THOUSAND_NOTES_QUANTITY;
        this.fiveThousandNotesQuantity = INITIAL_FIVE_THOUSAND_NOTES_QUANTITY;
    }

    public Note ejectNote(Denomination denomination) {
        decrementQuantity(denomination);

        return new Note(denomination);
    }

    public void insertNote(Note note) {
        incrementQuantity(note.getDenomination());
    }

    private void decrementQuantity(Denomination denomination) {
        switch (denomination) {
            case ONE_HUNDRED:
                oneHundredNotesQuantity--;
            case FIVE_HUNDRED:
                fiveHundredNotesQuantity--;
            case ONE_THOUSAND:
                oneThousandNotesQuantity--;
            case FIVE_THOUSAND:
                fiveThousandNotesQuantity--;
        }
    }

    private void incrementQuantity(Denomination denomination) {
        switch (denomination) {
            case ONE_HUNDRED:
                oneHundredNotesQuantity++;
            case FIVE_HUNDRED:
                fiveHundredNotesQuantity++;
            case ONE_THOUSAND:
                oneThousandNotesQuantity++;
            case FIVE_THOUSAND:
                fiveThousandNotesQuantity++;
        }
    }

    public int getOneHundredNotesQuantity() {
        return oneHundredNotesQuantity;
    }

    public int getFiveHundredNotesQuantity() {
        return fiveHundredNotesQuantity;
    }

    public int getOneThousandNotesQuantity() {
        return oneThousandNotesQuantity;
    }

    public int getFiveThousandNotesQuantity() {
        return fiveThousandNotesQuantity;
    }
}
