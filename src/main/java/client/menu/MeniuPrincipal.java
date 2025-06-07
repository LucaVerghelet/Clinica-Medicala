package client.menu;

import client.io.ServiceComunicare;
import common.dto.*;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;

public class MeniuPrincipal extends Meniu {
    private ArrayList<String> optiuni;
    private ServiceComunicare serviceComunicare;

    public MeniuPrincipal(ServiceComunicare serviceComunicare) throws UnknownHostException {
        super();
        this.optiuni = new ArrayList<>(Arrays.asList("Inregistrare", "Login", "Oprire"));
        this.serviceComunicare = serviceComunicare;
    }

    public void afisare(MeniuInregistrare meniuInregistrare,
                        MeniuLogin meniuLogin,
                        MeniuDoctor meniuDoctor,
                        MeniuPacient meniuPacient) throws IOException, ClassNotFoundException {
        while (true) {
            Integer optiune = meniuSelectie(this.optiuni);
            switch (optiune) {
                case 0:
                    MesajFrontend<InregistrareUserDTO> dateUser = meniuInregistrare.inregistrareUser();
                    String raspuns = this.serviceComunicare.trimiteMesaj(dateUser);
                    System.out.println(raspuns);
                    break;
                case 1:
                    MesajFrontend<LoginDTO> mesajLogin = meniuLogin.afisare();
                    MesajBackend raspunsLogin = this.serviceComunicare.trimiteMesaj(mesajLogin);
                    switch (raspunsLogin.getTipUtilizator()) {
                        case TipUtilizator.PACIENT:
                            meniuPacient.setEmailPacient(raspunsLogin.getEmailUtilizator());
                            meniuPacient.afisare();
                            break;
                        case TipUtilizator.DOCTOR:
                            meniuDoctor.setEmailDoctor(raspunsLogin.getEmailUtilizator());
                            meniuDoctor.afisare();
                            break;
                    }
                    System.out.println(raspunsLogin);
                    break;
                case 2:
                    return;
                default:
                    System.out.println("Optiune invalida, va rugam sa incercati din nou.");
            }
        }
    }
}
