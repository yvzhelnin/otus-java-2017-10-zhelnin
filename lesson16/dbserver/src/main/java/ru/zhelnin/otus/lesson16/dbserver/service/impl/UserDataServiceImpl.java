package ru.zhelnin.otus.lesson16.dbserver.service.impl;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import ru.zhelnin.otus.lesson16.dbserver.model.UserData;
import ru.zhelnin.otus.lesson16.dbserver.properties.DBProperties;
import ru.zhelnin.otus.lesson16.dbserver.service.dao.UserDataDao;
import ru.zhelnin.otus.lesson16.dbserver.cache.ZCache;
import ru.zhelnin.otus.lesson16.dbserver.model.AddressData;
import ru.zhelnin.otus.lesson16.dbserver.model.PhoneData;
import ru.zhelnin.otus.lesson16.dbserver.service.UserDataService;

import java.sql.SQLException;
import java.util.Collection;
import java.util.logging.Logger;

public class UserDataServiceImpl implements UserDataService {

    private static final Logger logger = Logger.getLogger(UserDataServiceImpl.class.getName());

    private final SessionFactory sessionFactory;

    private final ZCache<Long, UserData> users;

    public UserDataServiceImpl(ZCache<Long, UserData> users) {
        this.users = users;
        Configuration configuration = new Configuration();

        configuration.addAnnotatedClass(UserData.class);
        configuration.addAnnotatedClass(AddressData.class);
        configuration.addAnnotatedClass(PhoneData.class);

        configuration.setProperties(DBProperties.getProperties());
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
            logger.info("Got user from cache");
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
}
