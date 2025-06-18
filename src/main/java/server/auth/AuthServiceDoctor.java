package server.auth;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import server.database.DoctorRepository;
import server.database.HibernateUtil;
import common.dto.InregistrareDoctorDTO;
import server.database.PacientRepository;
import server.model.Doctor;
import server.model.Specializare;
import server.model.UserProfile;

public class AuthServiceDoctor {
    private HibernateUtil hibernateUtil;
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
        System.out.println(specializare.getNumeSpecializare());
        em.persist(d);
        em.persist(u);

        transaction.commit();

        if (transaction.isActive()){
            transaction.rollback();
        }
        em.close();
    }
}
