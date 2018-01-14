package ru.zhelnin.otus.lesson10.service.dao;

import org.hibernate.SessionFactory;
import ru.zhelnin.otus.lesson10.model.PhoneData;

import java.sql.SQLException;

public class PhoneDataDao extends AbstractDao {

    public PhoneDataDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public void addPhone(PhoneData phone) throws SQLException {
        executeQuery(e -> sessionFactory.getCurrentSession().save(phone));
    }

    public PhoneData getPhoneById(long id) throws SQLException {
        return executeQuery(e -> sessionFactory.getCurrentSession().load(PhoneData.class, id));
    }
}
