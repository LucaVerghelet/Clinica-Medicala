package client;

import client.io.ServiceComunicare;
import client.menu.MeniuInregistrare;
import client.menu.MeniuPrincipal;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ServiceComunicare serviceComunicare = new ServiceComunicare();
        MeniuPrincipal meniuPrincipal = new MeniuPrincipal(serviceComunicare);
        MeniuInregistrare meniuInregistrare = new MeniuInregistrare();
        meniuPrincipal.afisare(meniuInregistrare);
    }
}