package ru.zhelnin.otus.lesson7.core.atm.state;

import ru.zhelnin.otus.lesson7.core.atm.CasseteHandler;

public class AtmState {

    private final CasseteHandler savedCassete;

    public AtmState(CasseteHandler savedCassete) {
        this.savedCassete = savedCassete;
    }

    public CasseteHandler getSavedCassete() {
        return savedCassete;
    }
}
