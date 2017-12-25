package ru.zhelnin.otus.lesson10.service.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.zhelnin.otus.lesson10.model.UserData;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.sql.SQLException;
import java.util.Collection;
import java.util.function.Function;

public class UserDataDao {

    private final Session session;

    public UserDataDao(Session session) {
        this.session = session;
    }

    public void createUser(UserData user) throws SQLException {
        executeQuery(e -> session.save(user));
    }

    public void saveUser(UserData user) throws SQLException {
        executeQuery(e -> {
            session.update(user);

            return null;
        });
    }

    public UserData getUserById(long id) throws SQLException {
        return executeQuery(e -> session.load(UserData.class, id));
    }

    public Collection<UserData> getAll() throws SQLException {
        return executeQuery(e -> {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<UserData> query = builder.createQuery(UserData.class);
            query.from(UserData.class);

            return session.createQuery(query).list();
        });
    }

    private <T> T executeQuery(Function<Session, T> function) {
        Transaction transaction = session.beginTransaction();
        T result = function.apply(session);
        transaction.commit();

        return result;
    }
}
