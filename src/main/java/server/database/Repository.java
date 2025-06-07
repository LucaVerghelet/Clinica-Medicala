package server.database;

import jakarta.persistence.EntityManager;

public class Repository {
    protected EntityManager em;

    public Repository(HibernateUtil hibernateUtil) {
        this.em = hibernateUtil.getEntityManager();
    }
}