package ru.zhelnin.otus.lesson7.core.transaction.command;

import ru.zhelnin.otus.lesson7.core.transaction.Deposit;
import ru.zhelnin.otus.lesson7.core.transaction.abstraction.Command;
import ru.zhelnin.otus.lesson7.core.transaction.abstraction.Transaction;

public class DepositCommand implements Command {

    public Integer execute(Transaction transaction) {
        Deposit deposit = (Deposit) transaction;

        return deposit.deposit();
    }
}
