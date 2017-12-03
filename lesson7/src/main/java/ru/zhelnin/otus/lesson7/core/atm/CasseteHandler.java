package ru.zhelnin.otus.lesson7.core.atm;

import ru.zhelnin.otus.lesson7.note.Denomination;
import ru.zhelnin.otus.lesson7.note.util.exception.NoSuchDenominationException;

public class CasseteHandler {

    private Integer oneHundredNotesAmount;
    private Integer fiveHundredNotesAmount;
    private Integer oneThousandNotesAmount;
    private Integer fiveThousandNotesAmount;

    public CasseteHandler(Integer oneHundredNotesAmount, Integer fiveHundredNotesAmount, Integer oneThousandNotesAmount, Integer fiveThousandNotesAmount) {
        this.oneHundredNotesAmount = oneHundredNotesAmount;
        this.fiveHundredNotesAmount = fiveHundredNotesAmount;
        this.oneThousandNotesAmount = oneThousandNotesAmount;
        this.fiveThousandNotesAmount = fiveThousandNotesAmount;
    }

    CasseteHandler(CasseteHandler cassete) {
        this.oneHundredNotesAmount = cassete.getOneHundredNotesAmount();
        this.fiveHundredNotesAmount = cassete.getFiveHundredNotesAmount();
        this.oneThousandNotesAmount = cassete.getOneThousandNotesAmount();
        this.fiveThousandNotesAmount = cassete.getFiveThousandNotesAmount();
    }

    private Integer getOneHundredNotesAmount() {
        return oneHundredNotesAmount;
    }

    private Integer getFiveHundredNotesAmount() {
        return fiveHundredNotesAmount;
    }

    private Integer getOneThousandNotesAmount() {
        return oneThousandNotesAmount;
    }

    private Integer getFiveThousandNotesAmount() {
        return fiveThousandNotesAmount;
    }

    public void getNotes(Denomination denomination) throws NoSuchDenominationException {
        switch (denomination) {
            case ONE_HUNDRED:
                if (oneHundredNotesAmount == 0) {
                    throw new NoSuchDenominationException();
                }
                oneHundredNotesAmount--;
                break;
            case FIVE_HUNDRED:
                if (fiveHundredNotesAmount == 0) {
                    throw new NoSuchDenominationException();
                }
                fiveHundredNotesAmount--;
                break;
            case ONE_THOUSAND:
                if (oneThousandNotesAmount == 0) {
                    throw new NoSuchDenominationException();
                }
                oneThousandNotesAmount--;
                break;
            case FIVE_THOUSAND:
                if (fiveThousandNotesAmount == 0) {
                    throw new NoSuchDenominationException();
                }
                fiveThousandNotesAmount--;
                break;
            default:
                throw new NoSuchDenominationException();
        }
    }

    @Override
    public String toString() {
        return "oneHundredNotesAmount = " + oneHundredNotesAmount +
                "; fiveHundredNotesAmount = " + fiveHundredNotesAmount +
                "; oneThousandNotesAmount = " + oneThousandNotesAmount +
                "; fiveThousandNotesAmount = " + fiveThousandNotesAmount;
    }
}
