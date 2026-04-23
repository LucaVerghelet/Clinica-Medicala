package client.menu;
import common.dto.ProgramareDTO;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class MeniuCreeareProgramare {
    public ProgramareDTO afisare(String emailDoctor, String emailUser) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduceti data programarii (format: YYYY-MM-DD): ");
        String data = scanner.nextLine();
        System.out.println("Introduceti ora programarii (format: HH:MM): ");
        String ora = scanner.nextLine();

        System.out.println("Introduceti comentariul pentru programare: ");
        String comentariu = scanner.nextLine();

        ProgramareDTO programare = new ProgramareDTO();
        programare.setEmailPacient(emailUser);
        programare.setEmailDoctor(emailDoctor);
        programare.setData(LocalDate.parse(data));
        programare.setOra(LocalTime.parse(ora));
        programare.setComentariu(comentariu);
        return programare;
    }
}
