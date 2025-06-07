package client;

import client.io.ServiceComunicare;
import client.menu.*;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ServiceComunicare serviceComunicare = new ServiceComunicare();
        MeniuPrincipal meniuPrincipal = new MeniuPrincipal(serviceComunicare);
        MeniuInregistrare meniuInregistrare = new MeniuInregistrare();
        MeniuProgramari meniuProgramari = new MeniuProgramari();
        MeniuDoctor meniuDoctor = new MeniuDoctor(meniuProgramari, serviceComunicare);
        MeniuSelectareDoctor meniuSelectareDoctor = new MeniuSelectareDoctor();
        MeniuCreeareProgramare meniuCreeareProgramare = new MeniuCreeareProgramare();
        MeniuPacient meniuPacient = new MeniuPacient(meniuProgramari, serviceComunicare,
                meniuSelectareDoctor, meniuCreeareProgramare);
        MeniuLogin meniuLogin = new MeniuLogin();
        meniuPrincipal.afisare(meniuInregistrare,
                meniuLogin,
                meniuDoctor,
                meniuPacient);
    }
}