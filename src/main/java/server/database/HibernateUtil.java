package server.database;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static final EntityManagerFactory emf = buildEMF();

    private static EntityManagerFactory buildEMF() {
        return Persistence.createEntityManagerFactory("hibernate-jpa-unit");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
