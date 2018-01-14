package ru.zhelnin.otus.lesson10.service.impl;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import ru.zhelnin.otus.lesson10.model.AddressData;
import ru.zhelnin.otus.lesson10.model.PhoneData;
import ru.zhelnin.otus.lesson10.model.UserData;
import ru.zhelnin.otus.lesson10.properties.AppProperties;
import ru.zhelnin.otus.lesson10.service.UserDataService;
import ru.zhelnin.otus.lesson10.service.dao.UserDataDao;

import java.sql.SQLException;
import java.util.Collection;

public class UserDataServiceImpl implements UserDataService {

    private final SessionFactory sessionFactory;
    private final UserDataDao userDataDao;

    public UserDataServiceImpl() {
        Configuration configuration = new Configuration();

        configuration.addAnnotatedClass(UserData.class);
        configuration.addAnnotatedClass(AddressData.class);
        configuration.addAnnotatedClass(PhoneData.class);

        configuration.setProperties(AppProperties.getProperties());
        sessionFactory = createSessionFactory(configuration);
        userDataDao = new UserDataDao(sessionFactory);
    }

    private static SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());

        return configuration.buildSessionFactory(builder.build());
    }

    public void createUser(UserData user) throws SQLException {
        userDataDao.createUser(user);
    }

    public void saveUser(UserData user) throws SQLException {
        userDataDao.saveUser(user);
    }

    public UserData getUserById(long id) throws SQLException {
        return userDataDao.getUserById(id);
    }

    public Collection<UserData> getAll() throws SQLException {
        return userDataDao.getAll();
    }

    public void close() {
        sessionFactory.close();
    }
}
