package client.menu;

import client.io.ServiceComunicare;
import common.dto.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class MeniuPacient extends Meniu {
    private String emailPacient;
    private MeniuProgramari meniuProgramari;
    private ServiceComunicare serviceComunicare;
    private MeniuSelectareDoctor meniuSelectareDoctor;
    private MeniuCreeareProgramare meniuCreeareProgramare;

    private ArrayList<String> optiuni = new ArrayList<>(Arrays.asList(
            "Vizualizare programari",
            "Creeare programare",
            "Logout"
    ));

    public MeniuPacient(MeniuProgramari meniuProgramari,
                        ServiceComunicare serviceComunicare,
                        MeniuSelectareDoctor meniuSelectareDoctor,
                        MeniuCreeareProgramare meniuCreeareProgramare) {
        super();
        this.meniuProgramari = meniuProgramari;
        this.serviceComunicare = serviceComunicare;
        this.meniuSelectareDoctor = meniuSelectareDoctor;
        this.meniuCreeareProgramare = meniuCreeareProgramare;
    }

    public void setEmailPacient(String emailPacient) {
        this.emailPacient = emailPacient;
    }

    public String getEmailPacient() {
        return emailPacient;
    }

    public void afisare() throws IOException, ClassNotFoundException {
        while (true) {
            System.out.println("Meniu Pacient (" + emailPacient + ")");
            Integer optiune = meniuSelectie(this.optiuni);
            switch (optiune) {
                case 0:
                    MesajFrontend<String> mesajProgramari = new MesajFrontend<String>(TipOperatie.VIZUALIZARE_PROGRAMARI_PACIENT,
                            emailPacient);
                    MesajBackend<ArrayList<ProgramareDTO>> raspunsProgramari = this.serviceComunicare.trimiteMesaj(mesajProgramari);
                    this.meniuProgramari.afisarePacient(raspunsProgramari.getDto());
                    break;
                case 1:
                    MesajFrontend<String> mesajSelectareDoctori = new MesajFrontend<>(TipOperatie.VIZUALIZARE_DOCTORI,
                            emailPacient);
                    MesajBackend<ArrayList<DoctorDTO>> mesajDoctori = this.serviceComunicare.trimiteMesaj(mesajSelectareDoctori);
                    meniuSelectareDoctor.setDoctori(mesajDoctori.getDto());
                    DoctorDTO doctorSelectat = this.meniuSelectareDoctor.afisare();

                    ProgramareDTO programare = this.meniuCreeareProgramare.afisare(doctorSelectat.getEmail(), emailPacient);
                    MesajFrontend<ProgramareDTO> mesajCreeareProgramare = new MesajFrontend<>(TipOperatie.CREARE_PROGRAMARE,
                            programare);
                    MesajBackend<String> raspunsCreeareProgramareFinal = this.serviceComunicare.trimiteMesaj(mesajCreeareProgramare);
                    System.out.println(raspunsCreeareProgramareFinal.getMesaj());
                    break;
                case 2:
                    return;
                default:
                    System.out.println("Optiune invalida, va rugam sa incercati din nou.");
            }
        }
    }
}
