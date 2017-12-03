package ru.zhelnin.otus.lesson7.core.atm.state;

public class AtmMemento {

    private final AtmState savedState;

    public AtmMemento(AtmState stateToSave) {
        this.savedState = stateToSave;
    }

    public AtmState getSavedState() {
        return savedState;
    }
}
