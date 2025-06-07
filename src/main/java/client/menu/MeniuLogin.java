package client.menu;

import common.dto.InregistrareUserDTO;
import common.dto.LoginDTO;
import common.dto.MesajFrontend;
import common.dto.TipOperatie;

import java.util.Scanner;

public class MeniuLogin {
    public MesajFrontend<LoginDTO> afisare() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduceti adresa de e-mail: ");
        String email = sc.nextLine();
        System.out.println("Introduceti parola: ");
        String parola = sc.nextLine();
        LoginDTO loginDTO = new LoginDTO(email, parola);
        return new MesajFrontend<>(TipOperatie.LOGIN, loginDTO);
    }
}
