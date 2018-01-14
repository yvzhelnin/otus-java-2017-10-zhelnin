package ru.zhelnin.otus.lesson10.service.dao;

import org.hibernate.SessionFactory;
import ru.zhelnin.otus.lesson10.model.AddressData;

import java.sql.SQLException;

public class AddressDataDao extends AbstractDao {

    public AddressDataDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public void addAddress(AddressData address) throws SQLException {
        executeQuery(e -> sessionFactory.getCurrentSession().save(address));
    }

    public AddressData getAddressById(long id) throws SQLException {
        return executeQuery(e -> sessionFactory.getCurrentSession().load(AddressData.class, id));
    }
}
