package ru.zhelnin.otus.lesson16.dbserver.service.dao;

import org.hibernate.Session;
import ru.zhelnin.otus.lesson16.dbserver.model.PhoneData;

import java.sql.SQLException;

public class PhoneDataDao extends AbstractDao {

    public PhoneDataDao(Session session) {
        super(session);
    }

    public void addPhone(PhoneData phone) throws SQLException {
        executeQuery(e -> session.save(phone));
    }

    public PhoneData getPhoneById(long id) throws SQLException {
        return executeQuery(e -> session.load(PhoneData.class, id));
    }
}
