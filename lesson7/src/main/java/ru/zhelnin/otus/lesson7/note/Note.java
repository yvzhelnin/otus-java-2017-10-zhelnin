package ru.zhelnin.otus.lesson7.note;

public class Note {

    private final Denomination denomination;

    public Note(Denomination denomination) {
        this.denomination = denomination;
    }

    public Denomination getDenomination() {
        return denomination;
    }
}
