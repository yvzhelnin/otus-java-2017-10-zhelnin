package ru.zhelnin.otus.lesson7.core.transaction.command;

import ru.zhelnin.otus.lesson7.core.transaction.BalanceView;
import ru.zhelnin.otus.lesson7.core.transaction.abstraction.Command;
import ru.zhelnin.otus.lesson7.core.transaction.abstraction.Transaction;

public class BalanceViewCommand implements Command {

    public Integer execute(Transaction transaction) {
        BalanceView balanceView = (BalanceView) transaction;

        return balanceView.view();
    }
}
