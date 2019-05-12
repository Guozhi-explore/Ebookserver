package util;

import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.registry.internal.StandardServiceRegistryImpl;
import org.hibernate.cfg.Configuration;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static SessionFactory sessionFactory = null;

    public static SessionFactory getSessionFactory()
    {

        Configuration configuration = new Configuration().configure();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        StandardServiceRegistryImpl registry = (StandardServiceRegistryImpl) builder.build();
        sessionFactory = configuration.buildSessionFactory(registry);
        return sessionFactory;

    }

}

