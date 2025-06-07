package org.example.database;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static final EntityManagerFactory emf = buildEMF();

    private static EntityManagerFactory buildEMF() {

//        try{
            return Persistence.createEntityManagerFactory("hibernate-jpa-unit");
//        } catch (Exception ex) {
//            throw new ExceptionInInitializerError("Couldn't connect");
//        }
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    public static SessionFactory getCurrentSessionFactoryFromConfig() {
        // SessionFactory in Hibernate 5 example
        Configuration config = new Configuration();
        config.configure();
        // local SessionFactory bean created
        SessionFactory sessionFactory = config.buildSessionFactory();
        return sessionFactory;
    }
}
