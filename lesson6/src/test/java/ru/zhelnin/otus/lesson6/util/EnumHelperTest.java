package ru.zhelnin.otus.lesson6.util;

import org.junit.Assert;
import org.junit.Test;
import ru.zhelnin.otus.lesson6.core.transaction.TransactionType;
import ru.zhelnin.otus.lesson6.note.Denomination;

import java.util.Arrays;
import java.util.List;

public class EnumHelperTest {

    @Test
    public void getAvailableValuesForDenomination() {
        List<Integer> expected = Arrays.asList(100, 500, 1000, 5000);
        Assert.assertEquals(expected, new EnumHelper<Denomination, Integer>().getAvailableValues(Denomination.class, Denomination::getValue));
    }

    @Test
    public void getAvailableValuesForTransactionType() {
        List<String> expected = Arrays.asList("1", "2", "3");
        Assert.assertEquals(expected, new EnumHelper<TransactionType, String>().getAvailableValues(TransactionType.class, TransactionType::getCode));
    }
}
