package ru.zhelnin.otus.lesson7.core.transaction.command;

import ru.zhelnin.otus.lesson7.core.transaction.Withdrawal;
import ru.zhelnin.otus.lesson7.core.transaction.abstraction.Command;
import ru.zhelnin.otus.lesson7.core.transaction.abstraction.Transaction;
import ru.zhelnin.otus.lesson7.note.Note;
import ru.zhelnin.otus.lesson7.note.util.exception.NoSuchDenominationException;

import java.util.Collection;

public class WithdrawalCommand implements Command {

    public Collection<Note> execute(Transaction transaction) throws NoSuchDenominationException {
        Withdrawal withdrawal = (Withdrawal) transaction;

        return withdrawal.withdraw();
    }
}
