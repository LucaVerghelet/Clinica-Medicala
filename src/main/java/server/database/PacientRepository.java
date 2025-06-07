package server.database;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import server.model.Pacient;

public class PacientRepository extends Repository {

    public PacientRepository(HibernateUtil hibernateUtil) {
        super(hibernateUtil);
    }

    public Pacient findByEmail(String email) {
        try {
            return this.em.createQuery(
                            "SELECT p FROM Pacient p WHERE p.userProfile.email = :email", Pacient.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

}
