package ru.zhelnin.otus.lesson16.dbserver.service.dao;

import org.hibernate.Session;
import ru.zhelnin.otus.lesson16.dbserver.model.AddressData;

import java.sql.SQLException;

public class AddressDataDao extends AbstractDao {

    public AddressDataDao(Session session) {
        super(session);
    }

    public void addAddress(AddressData address) throws SQLException {
        executeQuery(e -> session.save(address));
    }

    public AddressData getAddressById(long id) throws SQLException {
        return executeQuery(e -> session.load(AddressData.class, id));
    }
}
