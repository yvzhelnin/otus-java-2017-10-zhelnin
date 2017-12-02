package ru.zhelnin.otus.lesson7.core.transaction.abstraction;

import ru.zhelnin.otus.lesson7.note.util.exception.NoSuchDenominationException;

@FunctionalInterface
public interface Command {

    <T> T execute(Transaction transaction) throws NoSuchDenominationException;
}
