package client.menu;

import common.dto.DoctorDTO;

import java.util.ArrayList;

public class MeniuSelectareDoctor extends Meniu {
    private ArrayList<DoctorDTO> doctori;
    public MeniuSelectareDoctor() {
        super();
    }

    public void setDoctori(ArrayList<DoctorDTO> doctori) {
        this.doctori = doctori;
    }

    public DoctorDTO afisare() {
        System.out.println("Selecteaza un doctor:");
        ArrayList<String> optiuni = new ArrayList<>();
        for (DoctorDTO doctor : doctori) {
            optiuni.add(doctor.toString());
        }
        Integer optiune = this.meniuSelectie(optiuni);
        if (optiune < 0 || optiune >= doctori.size()) {
            System.out.println("Optiune invalida. Te rog sa incerci din nou.");
            return null;
        } else {
            return doctori.get(optiune - 1);
        }
    }
}
