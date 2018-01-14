package ru.zhelnin.otus.lesson10.service.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.function.Function;

class AbstractDao {

    final SessionFactory sessionFactory;

    AbstractDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    <T> T executeQuery(Function<Session, T> function) {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        T result = function.apply(currentSession);
        transaction.commit();

        return result;
    }
}
