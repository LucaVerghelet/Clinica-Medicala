package client.menu;

import common.dto.ProgramareDTO;

import java.util.ArrayList;

public class MeniuProgramari {

    public void afisareDoctor(ArrayList<ProgramareDTO> programari) {
        System.out.println("Programari");
        if (programari.isEmpty()) {
            System.out.println("Nu exista programari.");
            return;
        }

        for (ProgramareDTO programare : programari) {
            System.out.println(programare.toStringPentruDoctor());
        }
    }

    public void afisarePacient(ArrayList<ProgramareDTO> programari) {
        System.out.println("Programari");
        if (programari.isEmpty()) {
            System.out.println("Nu exista programari.");
            return;
        }

        for (ProgramareDTO programare : programari) {
            System.out.println(programare.toStringPentruPacient());
        }
    }

}
