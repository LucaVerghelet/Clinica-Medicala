package client.menu;

import java.util.ArrayList;
import java.util.Scanner;

public class Meniu {
    protected Integer meniuSelectie(ArrayList<String> optiuni) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Selecati optinea: ");
        for (int i = 0; i < optiuni.size(); i++) {
            System.out.println(Integer.toString(i + 1) + ". " + optiuni.get(i));
        }
        int optiune = sc.nextInt();
        return optiune - 1;
    }
}
