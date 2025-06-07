package org.example.auth;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.example.database.HibernateUtil;
import org.example.dto.InregistrareDoctorDTO;
import org.example.dto.InregistrarePacientDTO;
import org.example.model.Doctor;
import org.example.model.Pacient;
import org.example.model.Specializare;
import org.example.model.UserProfile;

public class AuthServiceDoctor {
    private final HibernateUtil hibernateUtil;
    public AuthServiceDoctor(HibernateUtil hibernateUtil) {
        this.hibernateUtil = hibernateUtil;
    }

    public void inregistrare(InregistrareDoctorDTO inregistrareDoctorDTO) {
        EntityManager em = hibernateUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();

        transaction.begin();
        Specializare specializare = Specializare.findByNume(inregistrareDoctorDTO.getNumeSpecializare(), em);
        UserProfile u = new UserProfile(inregistrareDoctorDTO.getUsername(), inregistrareDoctorDTO.getEmail(), inregistrareDoctorDTO.getParola());
        Doctor d = new Doctor(inregistrareDoctorDTO.getNume(), inregistrareDoctorDTO.getPrenume(), specializare ,u);
        em.persist(d);
        em.persist(u);

        transaction.commit();

        if (transaction.isActive()){
            transaction.rollback();
        }
        em.close();
    }
}
