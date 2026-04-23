package client.menu;

import common.dto.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MeniuInregistrare {

    public MesajFrontend<InregistrareUserDTO> inregistrareUser() {
        Scanner sc = new Scanner(System.in);
        System.out.println("In cazul in care sunteti doctor introduceti 1. In cazul in care sunteti pacient introduceti 2.");
        int optiune = sc.nextInt();
        MesajFrontend<InregistrareUserDTO> dateUser = null;
        switch(optiune) {
            case 1:
                dateUser = this.inregistrareDoctor();
                break;
            case 2:
                dateUser = this.inregistrarePacient();
                break;
            default:
                System.out.println("Optiunea aleasa este invalida");
        }
        return dateUser;
    }

    public MesajFrontend<InregistrareUserDTO> inregistrarePacient () {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduceti username: ");
        String username = sc.nextLine();
        System.out.println("Introduceti adresa de e-mail:");
        String email = sc.nextLine();
        System.out.println("Introduceti parola: ");
        String parola = sc.nextLine();
        System.out.println("Introduceti CNP:");
        String cnp = sc.nextLine();
        System.out.println("In cazul in care aveti alergii va rugam sa le introduceti: ");
        String alergii = sc.nextLine();
        System.out.println("Introduceti numele dvs. : ");
        String nume = sc.nextLine();
        System.out.println("Introduceti prenumele dvs. :");
        String prenume = sc.nextLine();

        InregistrarePacientDTO date = new InregistrarePacientDTO(username, email, parola, cnp, alergii, nume, prenume);
        return new MesajFrontend<>(TipOperatie.INREGISTRARE_PACIENT, date);
    }

    public MesajFrontend<InregistrareUserDTO> inregistrareDoctor () {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduceti username: ");
        String username = sc.nextLine();
        System.out.println("Introduceti adresa de e-mail:");
        String email = sc.nextLine();
        System.out.println("Introduceti parola: ");
        String parola = sc.nextLine();
        ArrayList<String> listSpecializari = new ArrayList<>(Arrays.asList("ORL", "Dentist", "Cardiolog"));
        String numeSpecializare = this.meniuSelectareSpecializare(listSpecializari);
        System.out.println("Introduceti numele dvs. : ");
        String nume = sc.nextLine();
        System.out.println("Introduceti prenumele dvs. :");
        String prenume = sc.nextLine();
        InregistrareDoctorDTO date = new InregistrareDoctorDTO(username, email, parola, numeSpecializare, nume, prenume);
        return new MesajFrontend<>(TipOperatie.INREGISTRARE_DOCTOR, date);
    }

    public String meniuSelectareSpecializare(ArrayList <String> listSpecializari) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduceti numarul specializarii. Optiunile sunt: ");
        for (int i = 0; i < listSpecializari.size(); i++) {
            System.out.println(Integer.toString(i + 1) + ". " + listSpecializari.get(i));
        }
        int optiuneAleasa = sc.nextInt();
        return listSpecializari.get(optiuneAleasa - 1);
    }
}
