package ru.zhelnin.otus.lesson7.util;

import org.junit.Assert;
import org.junit.Test;
import ru.zhelnin.otus.lesson7.core.transaction.TransactionType;
import ru.zhelnin.otus.lesson7.note.Denomination;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class EnumHelperTest {

    @Test
    public void getAvailableValuesForDenomination() {
        Set<Integer> expected = new HashSet<>(Arrays.asList(100, 500, 1000, 5000));
        Assert.assertEquals(expected, new EnumHelper<Denomination, Integer>().getAvailableValues(Denomination.class, Denomination::getValue));
    }

    @Test
    public void getAvailableValuesForTransactionType() {
        Set<String> expected = new HashSet<>(Arrays.asList("1", "2", "3"));
        Assert.assertEquals(expected, new EnumHelper<TransactionType, String>().getAvailableValues(TransactionType.class, TransactionType::getCode));
    }
}
