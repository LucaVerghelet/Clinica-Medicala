package server.database;
import common.dto.DoctorDTO;
import jakarta.persistence.NoResultException;
import server.model.Doctor;

import java.util.ArrayList;

public class DoctorRepository extends Repository {
    public DoctorRepository(HibernateUtil hibernateUtil) {
        super(hibernateUtil);
    }

    public Doctor findByEmail(String email) {
        try {
            return this.em.createQuery(
                            "SELECT d FROM Doctor d WHERE d.userProfile.email = :email", Doctor.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public ArrayList<DoctorDTO> findAll() {
        try {
            // Also load specializare and userProfile eagerly
            ArrayList<Doctor> doctors = (ArrayList<Doctor>) this.em.createQuery(
                            "SELECT d FROM Doctor d JOIN FETCH d.specializare JOIN FETCH d.userProfile", Doctor.class)
                    .getResultList();
            ArrayList<DoctorDTO> doctorDTOs = new ArrayList<>();
            // doctor.specialzare is not being loaded
            // eagerly, so we need to fetch it explicitly
            for (Doctor doctor : doctors) {
                doctorDTOs.add(doctor.toDto());
            }
            return doctorDTOs;
        } catch (NoResultException e) {
            return new ArrayList<>();
        }
    }
}
