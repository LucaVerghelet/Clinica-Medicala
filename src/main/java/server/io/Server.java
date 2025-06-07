package server.io;

import common.dto.*;
import server.auth.AuthServiceDoctor;
import server.auth.AuthServicePacient;
import server.auth.LoginService;
import server.database.DoctorRepository;
import server.database.HibernateUtil;
import server.database.PacientRepository;
import server.database.ProgramareRepository;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    private static ServerSocket server;
    private static int port = 8010;


    private <T> Object procesareOperatie(MesajFrontend<T> mesajFrontend,
                                         AuthServiceDoctor authServiceDoctor,
                                         AuthServicePacient authServicePacient,
                                         LoginService loginService,
                                         ProgramareRepository programareRepository,
                                         DoctorRepository doctorRepository,
                                         PacientRepository pacientRepository) {
        try {
            Object raspunsMesaj = null;
            switch (mesajFrontend.getTipOperatie()) {
                case TipOperatie.INREGISTRARE_PACIENT:
                    InregistrarePacientDTO dtoInregistrarePacient = (InregistrarePacientDTO) mesajFrontend.getDto();
                    authServicePacient.inregistrare(dtoInregistrarePacient);
                    raspunsMesaj = "Inregistrare efectuata cu succes.";
                    System.out.println(raspunsMesaj);
                    break;
                case TipOperatie.INREGISTRARE_DOCTOR:
                    InregistrareDoctorDTO dtoInregistrareDoctor = (InregistrareDoctorDTO) mesajFrontend.getDto();
                    authServiceDoctor.inregistrare(dtoInregistrareDoctor);
                    raspunsMesaj = "Inregistrare efectuata cu succes.";
                    System.out.println(raspunsMesaj);
                    break;
                case TipOperatie.LOGIN:
                    MesajBackend mesajLogin = loginService.login((LoginDTO) mesajFrontend.getDto());
                    raspunsMesaj = mesajLogin;
                    System.out.println(mesajLogin.getMesaj());
                    break;
                case TipOperatie.VIZUALIZARE_PROGRAMARI_DOCTOR:
                    String emailDoctor = (String) mesajFrontend.getDto();
                    ArrayList<ProgramareDTO> programariDoctor = programareRepository
                            .findByDoctorEmail(emailDoctor);
                    MesajBackend<ArrayList<ProgramareDTO>> mesajBackandProgramari = new MesajBackend<>(
                            "Programari (" + programariDoctor.size() + ") gasite",
                            emailDoctor,
                            TipUtilizator.DOCTOR);
                    mesajBackandProgramari.setDto(programariDoctor);
                    raspunsMesaj = mesajBackandProgramari;
                    break;
                case TipOperatie.VIZUALIZARE_PROGRAMARI_PACIENT:
                    String emailPacient = (String) mesajFrontend.getDto();
                    ArrayList<ProgramareDTO> programariPacient = programareRepository
                            .findByPacientEmail(emailPacient);
                    MesajBackend<ArrayList<ProgramareDTO>> mesajBackandProgramariPacient = new MesajBackend<>(
                            "Programari (" + programariPacient.size() + ") gasite",
                            emailPacient,
                            TipUtilizator.PACIENT);
                    mesajBackandProgramariPacient.setDto(programariPacient);
                    raspunsMesaj = mesajBackandProgramariPacient;
                    break;
                case TipOperatie.VIZUALIZARE_DOCTORI:
                    String emailPacientPentruDoctori = (String) mesajFrontend.getDto();
                    ArrayList<DoctorDTO> doctori = doctorRepository.findAll();
                    MesajBackend<ArrayList<DoctorDTO>> mesajBackandDoctori = new MesajBackend<>(
                            "Doctori (" + doctori.size() + ") gasiti",
                            emailPacientPentruDoctori,
                            TipUtilizator.PACIENT);
                    mesajBackandDoctori.setDto(doctori);
                    raspunsMesaj = mesajBackandDoctori;
                    break;
                case TipOperatie.CREARE_PROGRAMARE:
                    ProgramareDTO programareDTO = (ProgramareDTO) mesajFrontend.getDto();
                    programareRepository.creeazaProgramare(programareDTO,
                            doctorRepository,
                            pacientRepository);
                    raspunsMesaj = new MesajBackend<String>(
                            "Programare creata cu succes.",
                            programareDTO.getEmailPacient(),
                            TipUtilizator.PACIENT
                    );
                    System.out.println(raspunsMesaj);
                    break;
                default:
                    raspunsMesaj = "Metoda " + mesajFrontend.getTipOperatie().toString() + " nu a fost recunoscuta.";
                    System.out.println(raspunsMesaj);
                    break;
            }

            return raspunsMesaj;
        } catch (Exception e) {
            System.err.println(e.toString());
            return "Eroare: " + e.toString();
        }
    }

    public void start() throws IOException, ClassNotFoundException {
        server = new ServerSocket(port);
        HibernateUtil hibernateUtil = new HibernateUtil();
        PacientRepository pacientRepository = new PacientRepository(hibernateUtil);
        DoctorRepository doctorRepository = new DoctorRepository(hibernateUtil);
        AuthServiceDoctor authServiceDoctor = new AuthServiceDoctor(hibernateUtil);
        AuthServicePacient authServicePacient = new AuthServicePacient(hibernateUtil);
        LoginService loginService = new LoginService(hibernateUtil, pacientRepository, doctorRepository);
        ProgramareRepository programareRepository = new ProgramareRepository(hibernateUtil);
        while (true) {
            System.out.println("Waiting for the client request");
            Socket socket = server.accept();
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            MesajFrontend message = (MesajFrontend) ois.readObject();
            Object raspuns = procesareOperatie(message, authServiceDoctor, authServicePacient, loginService,
                    programareRepository, doctorRepository, pacientRepository);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(raspuns);
            ois.close();
            oos.close();
            socket.close();
        }
    }
}
