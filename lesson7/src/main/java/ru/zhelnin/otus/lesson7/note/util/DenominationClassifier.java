package ru.zhelnin.otus.lesson7.note.util;

import ru.zhelnin.otus.lesson7.note.Denomination;

public final class DenominationClassifier {

    private DenominationClassifier() {
    }

    public static final int MINIMAL_DENOMINATION = Denomination.ONE_HUNDRED.getValue();
    public static final int MIDDLE_DENOMINATION = Denomination.FIVE_HUNDRED.getValue();
    public static final int BIG_DENOMINATION = Denomination.ONE_THOUSAND.getValue();
    public static final int HUGE_DENOMINATION = Denomination.FIVE_THOUSAND.getValue();
}
