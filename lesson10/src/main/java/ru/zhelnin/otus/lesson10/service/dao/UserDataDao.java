package ru.zhelnin.otus.lesson10.service.dao;

import org.hibernate.SessionFactory;
import ru.zhelnin.otus.lesson10.model.UserData;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.sql.SQLException;
import java.util.Collection;

public class UserDataDao extends AbstractDao {

    public UserDataDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public void createUser(UserData user) throws SQLException {
        executeQuery(e -> sessionFactory.getCurrentSession().save(user));
    }

    public void saveUser(UserData user) throws SQLException {
        executeQuery(e -> {
            sessionFactory.getCurrentSession().update(user);

            return null;
        });
    }

    public UserData getUserById(long id) throws SQLException {
        return executeQuery(e -> sessionFactory.getCurrentSession().load(UserData.class, id));
    }

    public Collection<UserData> getAll() throws SQLException {
        return executeQuery(e -> {
            CriteriaBuilder builder = sessionFactory.getCurrentSession().getCriteriaBuilder();
            CriteriaQuery<UserData> query = builder.createQuery(UserData.class);
            query.from(UserData.class);

            return sessionFactory.getCurrentSession().createQuery(query).list();
        });
    }
}
