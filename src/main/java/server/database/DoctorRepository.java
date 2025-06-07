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
            ArrayList<Doctor> doctors = (ArrayList<Doctor>) this.em.createQuery(
                            "SELECT d FROM Doctor d", Doctor.class)
                    .getResultList();
            ArrayList<DoctorDTO> doctorDTOs = new ArrayList<>();
            for (Doctor doctor : doctors) {
                doctorDTOs.add(doctor.toDto());
            }
            return doctorDTOs;
        } catch (NoResultException e) {
            return new ArrayList<>();
        }
    }
}
