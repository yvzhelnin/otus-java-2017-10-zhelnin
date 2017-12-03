package ru.zhelnin.otus.lesson7.core.department;

import ru.zhelnin.otus.lesson7.core.atm.Atm;
import ru.zhelnin.otus.lesson7.core.atm.CasseteHandler;

public class AtmDepartment {

    private static final int DEPARTMENT_CAPACITY = 8;

    private static final Atm[] atms = new Atm[DEPARTMENT_CAPACITY];

    static {

    }

    public AtmDepartment() {
        makeAtms();
    }

    private void makeAtms() {
        for (int i = 0; i < DEPARTMENT_CAPACITY; i++) {
            atms[i] = new Atm(this, new CasseteHandler(i * 20 + 100, i * 10 + 50, i * 5 + 25, i * 2 + 12));
        }
    }

    public void restoreGroupToInitialStates() {
        for (Atm atm : atms) {
            atm.restoreToInitialState();
        }
    }

    public void printNotesAmount() {
        for (Atm atm : atms) {
            System.out.println(atm.getCassete().toString());
        }
    }

    public Atm getConcreteAtm(int number) {
        return atms[number];
    }
}
