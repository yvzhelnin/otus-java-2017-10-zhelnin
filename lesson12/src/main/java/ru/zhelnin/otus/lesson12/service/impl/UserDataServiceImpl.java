package ru.zhelnin.otus.lesson12.service.impl;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import ru.zhelnin.otus.lesson12.cache.ZCache;
import ru.zhelnin.otus.lesson12.model.AddressData;
import ru.zhelnin.otus.lesson12.model.PhoneData;
import ru.zhelnin.otus.lesson12.model.UserData;
import ru.zhelnin.otus.lesson12.properties.AppProperties;
import ru.zhelnin.otus.lesson12.service.UserDataService;
import ru.zhelnin.otus.lesson12.service.dao.UserDataDao;

import java.sql.SQLException;
import java.util.Collection;

public class UserDataServiceImpl implements UserDataService {

    private final SessionFactory sessionFactory;

    private final ZCache<Long, UserData> users;

    public UserDataServiceImpl(ZCache<Long, UserData> users) {
        this.users = users;
        Configuration configuration = new Configuration();

        configuration.addAnnotatedClass(UserData.class);
        configuration.addAnnotatedClass(AddressData.class);
        configuration.addAnnotatedClass(PhoneData.class);

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
        updateCache(user);
    }

    public void saveUser(UserData user) throws SQLException {
        new UserDataDao(sessionFactory.getCurrentSession()).saveUser(user);
        updateCache(user);
    }

    private void updateCache(UserData user) {
        users.putElement(user.getId(), user);
    }

    public UserData getUserById(long id) throws SQLException {
        UserData result = users.getElement(id);
        if (result == null) {
            result = new UserDataDao(sessionFactory.getCurrentSession()).getUserById(id);
            updateCache(result);
        } else {
            System.out.println("Got user from cache");
        }
        return result;
    }

    public Collection<UserData> getAllUsersFromCache() throws SQLException {
        return users.getAllElements();
    }

    public Collection<UserData> getAll() throws SQLException {
        return new UserDataDao(sessionFactory.getCurrentSession()).getAll();
    }

    public void close() {
        sessionFactory.close();
    }

    public void printCache() {
        System.out.println("\nPrinting current cache:\n");
        System.out.println(users);
        System.out.println("\n");
    }
}
