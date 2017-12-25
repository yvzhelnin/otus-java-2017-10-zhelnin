package ru.zhelnin.otus.lesson3.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;

/**
 * Created by 123 on 24/11/2017.
 */
public class PredicateTest<T> {

    <T> Collection<T> filter(Collection<T> c, Predicate<T> p) {
        Collection<T> result = new ArrayList<T>();
        for(T current : c) {
            if (p.test(current)) {
                result.add(current);
            }
        }
        return result;
    }

    <T> T reduce(Collection<T> c, BinaryOperator<T> o, T zero) {
        T result = zero;
        for(T t : c) {
            result = o.apply(t, result);
        }
        return result;
    }
}
