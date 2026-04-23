package server.auth;

import common.dto.LoginDTO;
import common.dto.MesajBackend;
import common.dto.TipUtilizator;
import jakarta.persistence.EntityManager;
import server.database.DoctorRepository;
import server.database.HibernateUtil;
import server.database.PacientRepository;
import server.model.Doctor;
import server.model.Pacient;
import server.model.UserProfile;

public class LoginService {
    private HibernateUtil hibernateUtil;
    private PacientRepository pacientRepository;
    private DoctorRepository doctorRepository;

    public LoginService(HibernateUtil hibernateUtil,
                        PacientRepository pacientRepository,
                        DoctorRepository doctorRepository) {
        this.hibernateUtil = hibernateUtil;
        this.pacientRepository = pacientRepository;
        this.doctorRepository = doctorRepository;
    }

    public MesajBackend login(LoginDTO loginDTO) {
        EntityManager em = this.hibernateUtil.getEntityManager();
        UserProfile user = em.find(UserProfile.class, loginDTO.getEmail());
        if (user == null) {
            throw new RuntimeException("Email-ul nu este inregistrat. Inregistrati-va inainte de a incerca sa va logati.");
        }
        if (!user.getPassword().equals(loginDTO.getParola())) {
            throw new RuntimeException("Parola nu este corecta.");
        }

        Pacient pacient = pacientRepository.findByEmail(loginDTO.getEmail());
        Doctor doctor = doctorRepository.findByEmail(loginDTO.getEmail());

        if (pacient != null) {
            return new MesajBackend<>(
                    "Logare ca pacient '" + loginDTO.getEmail() + "' cu succes.",
                    loginDTO.getEmail(),
                    TipUtilizator.PACIENT
            );
        } else if (doctor != null) {
            return new MesajBackend(
                    "Logare ca doctor '" + loginDTO.getEmail() + "' cu succes.",
                    loginDTO.getEmail(),
                    TipUtilizator.DOCTOR
            );
        } else {
            throw new RuntimeException("Utilizatorul nu este inregistrat ca pacient sau doctor.");
        }
    }
}
