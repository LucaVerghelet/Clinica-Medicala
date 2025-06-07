package client.menu;

import client.io.ServiceComunicare;
import common.dto.InregistrareUserDTO;
import common.dto.MesajFrontend;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MeniuPrincipal {
    private ArrayList<String> optiuni;
    private ServiceComunicare serviceComunicare;

    public MeniuPrincipal(ServiceComunicare serviceComunicare) throws UnknownHostException {
        this.optiuni = new ArrayList<>(Arrays.asList("Inregistrare", "Login", "Oprire"));
        this.serviceComunicare = serviceComunicare;
    }

    public void afisare(MeniuInregistrare meniuInregistrare) throws IOException, ClassNotFoundException {
        while (true) {
            Integer optiune = meniuSelectie(this.optiuni);
            switch (optiune) {
                case 0:
                    MesajFrontend<InregistrareUserDTO> dateUser = meniuInregistrare.inregistrareUser();
                    String raspuns = this.serviceComunicare.trimiteMesaj(dateUser);
                    System.out.println(raspuns);
                    break;
                case 1:
                    System.out.println("Functia de login nu este implementata inca.");
                    break;
                case 2:
                    return;
                default:
                    System.out.println("Optiune invalida, va rugam sa incercati din nou.");
            }
        }
    }

    public Integer meniuSelectie(ArrayList<String> optiuni) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Selecati optinea: ");
        for (int i = 0; i < optiuni.size(); i++) {
            System.out.println(Integer.toString(i + 1) + ". " + optiuni.get(i));
        }
        int optiune = sc.nextInt();
        return optiune - 1;
    }

}
