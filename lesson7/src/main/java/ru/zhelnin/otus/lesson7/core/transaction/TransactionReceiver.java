package ru.zhelnin.otus.lesson7.core.transaction;

import ru.zhelnin.otus.lesson7.core.transaction.abstraction.Command;
import ru.zhelnin.otus.lesson7.core.transaction.abstraction.Transaction;
import ru.zhelnin.otus.lesson7.core.transaction.command.BalanceViewCommand;
import ru.zhelnin.otus.lesson7.core.transaction.command.DepositCommand;
import ru.zhelnin.otus.lesson7.core.transaction.command.WithdrawalCommand;
import ru.zhelnin.otus.lesson7.note.util.exception.NoSuchDenominationException;

import java.util.HashMap;
import java.util.Map;

public class TransactionReceiver {

    private static Map<Transaction.Type, Command> transactions = new HashMap<>();

    static {
        transactions.put(Transaction.Type.DEPOSIT, new DepositCommand());
        transactions.put(Transaction.Type.BALANCE_VIEW, new BalanceViewCommand());
        transactions.put(Transaction.Type.WITHDRAWAL, new WithdrawalCommand());
    }

    public static <T> T accept(Transaction transaction) throws NoSuchDenominationException {
        return transactions.get(transaction.getType()).execute(transaction);
    }
}
