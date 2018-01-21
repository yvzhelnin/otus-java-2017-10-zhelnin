package ru.zhelnin.otus.lesson12.service.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.function.Function;

class AbstractDao {

    final Session session;

    AbstractDao(Session session) {
        this.session = session;
    }

    <T> T executeQuery(Function<Session, T> function) {
        Transaction transaction = session.beginTransaction();
        T result = function.apply(session);
        transaction.commit();

        return result;
    }
}
