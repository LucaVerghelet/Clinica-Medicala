package client.menu;

import client.io.ServiceComunicare;
import common.dto.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class MeniuDoctor extends Meniu {
    private MeniuProgramari meniuProgramari;
    private ServiceComunicare serviceComunicare;
    public MeniuDoctor(MeniuProgramari meniuProgramari,
                       ServiceComunicare serviceComunicare) {
        super();
        this.meniuProgramari = meniuProgramari;
        this.serviceComunicare = serviceComunicare;
    }

    private String emailDoctor;
    private ArrayList<String> optiuni = new ArrayList<>(Arrays.asList(
            "Vizualizare programari",
            "Prescriere tratament",
            "Adugare fisa pacient (diagnostic, tratament, prescriptie)",
            "Logout"
    ));

    public void setEmailDoctor(String emailUtilizator) {
        this.emailDoctor = emailUtilizator;
    }

    public void afisare() throws IOException, ClassNotFoundException {
        while (true) {
            System.out.println("Meniu Doctor (" + emailDoctor + ")");
            Integer optiune = meniuSelectie(this.optiuni);
            switch (optiune) {
                case 0:
                    MesajFrontend<String> mesajProgramari = new MesajFrontend<String>(TipOperatie.VIZUALIZARE_PROGRAMARI_DOCTOR,
                            emailDoctor);
                    MesajBackend<ArrayList<ProgramareDTO>> raspunsProgramari = this.serviceComunicare.trimiteMesaj(mesajProgramari);
                    this.meniuProgramari.afisareDoctor(raspunsProgramari.getDto());
                    break;
                case 1:
                   return;
                default:
                    System.out.println("Optiune invalida, va rugam sa incercati din nou.");
            }
        }
    }
}
