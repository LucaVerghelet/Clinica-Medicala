package org.example.auth;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.example.database.HibernateUtil;
import org.example.dto.InregistrarePacientDTO;
import org.example.model.Pacient;
import org.example.model.UserProfile;

public class AuthServicePacient {
    private final HibernateUtil hibernateUtil;
    public AuthServicePacient(HibernateUtil hibernateUtil) {
        this.hibernateUtil = hibernateUtil;
    }

    public void inregistrare(InregistrarePacientDTO inregistrarePacientDTO) {
        EntityManager em = hibernateUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();

        transaction.begin();
        UserProfile u = new UserProfile(inregistrarePacientDTO.getUsername(), inregistrarePacientDTO.getEmail(), inregistrarePacientDTO.getParola());
        Pacient p = new Pacient(inregistrarePacientDTO.getNume(), inregistrarePacientDTO.getPrenume(), inregistrarePacientDTO.getcnp(), inregistrarePacientDTO.getAlergii(), u);
        em.persist(p);
        em.persist(u);

        transaction.commit();

        if (transaction.isActive()){
            transaction.rollback();
        }
        em.close();
    }
}
