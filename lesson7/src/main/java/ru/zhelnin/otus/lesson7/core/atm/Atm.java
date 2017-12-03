package ru.zhelnin.otus.lesson7.core.atm;

import ru.zhelnin.otus.lesson7.core.account.Account;
import ru.zhelnin.otus.lesson7.core.atm.state.AtmMemento;
import ru.zhelnin.otus.lesson7.core.atm.state.AtmState;
import ru.zhelnin.otus.lesson7.core.department.AtmDepartment;

public class Atm {

    private static final Account ACCOUNT = Account.getInstance();

    private final AtmDepartment department;

    private CasseteHandler cassete;

    private final AtmMemento initialState;

    public Atm(AtmDepartment department, CasseteHandler cassete) {
        this.department = department;
        this.cassete = cassete;
        this.initialState = new AtmMemento(new AtmState(new CasseteHandler(cassete)));
    }

    public Account getAccount() {
        return ACCOUNT;
    }

    public AtmDepartment getDepartment() {
        return department;
    }

    public CasseteHandler getCassete() {
        return cassete;
    }

    public void restoreToInitialState() {
        this.cassete = initialState.getSavedState().getSavedCassete();
    }
}
