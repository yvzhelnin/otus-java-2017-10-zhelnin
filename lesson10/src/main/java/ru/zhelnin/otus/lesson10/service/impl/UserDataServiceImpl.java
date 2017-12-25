package ru.zhelnin.otus.lesson10.service.impl;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import ru.zhelnin.otus.lesson10.model.AddressData;
import ru.zhelnin.otus.lesson10.model.UserData;
import ru.zhelnin.otus.lesson10.properties.AppProperties;
import ru.zhelnin.otus.lesson10.service.UserDataService;
import ru.zhelnin.otus.lesson10.service.dao.UserDataDao;

import java.sql.SQLException;
import java.util.Collection;

public class UserDataServiceImpl implements UserDataService {

    private final SessionFactory sessionFactory;

    public UserDataServiceImpl() {
        Configuration configuration = new Configuration();

        configuration.addAnnotatedClass(UserData.class);
        configuration.addAnnotatedClass(AddressData.class);

        configuration.setProperties(AppProperties.getProperties());

        sessionFactory = createSessionFactory(configuration);
    }

    private static SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());

        return configuration.buildSessionFactory(builder.build());
    }

    public void createUser(UserData user) throws SQLException {
        new UserDataDao(sessionFactory.getCurrentSession()).createUser(user);
    }

    public void saveUser(UserData user) throws SQLException {
        new UserDataDao(sessionFactory.getCurrentSession()).saveUser(user);
    }

    public UserData getUserById(long id) throws SQLException {
        return new UserDataDao(sessionFactory.getCurrentSession()).getUserById(id);
    }

    public Collection<UserData> getAll() throws SQLException {
        return new UserDataDao(sessionFactory.getCurrentSession()).getAll();
    }

    public void close() {
        sessionFactory.close();
    }
}
