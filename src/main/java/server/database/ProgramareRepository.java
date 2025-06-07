package server.database;

import common.dto.ProgramareDTO;
import jakarta.persistence.NoResultException;
import server.model.Doctor;
import server.model.Pacient;
import server.model.Programare;

import java.util.ArrayList;

public class ProgramareRepository extends Repository{

    public ProgramareRepository(HibernateUtil hibernateUtil) {
        super(hibernateUtil);
    }

    public ArrayList<ProgramareDTO> findByDoctorEmail(String email) {
        try {
            ArrayList<Programare> programari = (ArrayList<Programare>) this.em.createQuery(
                            "SELECT p FROM Programare p WHERE p.doctor.userProfile.email = :email", Programare.class)
                    .setParameter("email", email)
                    .getResultList();
            ArrayList<ProgramareDTO> programariDTO = new ArrayList<>();
            for (Programare programare : programari) {
                programariDTO.add(programare.getProgramareDTO());
            }
            return programariDTO;
        } catch (NoResultException e) {
            return new ArrayList<>();
        }
    }

    public ArrayList<ProgramareDTO> findByPacientEmail(String email) {
        try {
            ArrayList<Programare> programari = (ArrayList<Programare>) this.em.createQuery(
                            "SELECT p FROM Programare p WHERE p.pacient.userProfile.email = :email", Programare.class)
                    .setParameter("email", email)
                    .getResultList();
            ArrayList<ProgramareDTO> programariDTO = new ArrayList<>();
            for (Programare programare : programari) {
                programariDTO.add(programare.getProgramareDTO());
            }
            return programariDTO;
        } catch (NoResultException e) {
            return new ArrayList<>();
        }
    }

    public void creeazaProgramare(ProgramareDTO programareDTO,
                                  DoctorRepository doctorRepository,
                                  PacientRepository pacientRepository) {
        this.em.getTransaction().begin();
        Doctor d = doctorRepository.findByEmail(programareDTO.getEmailDoctor());
        Pacient p = pacientRepository.findByEmail(programareDTO.getEmailPacient());
        this.em.persist(new Programare(
                programareDTO.getData(),
                programareDTO.getOra(),
                p,
                d,
                programareDTO.getComentariu()
        ));
        this.em.getTransaction().commit();
    }
}
