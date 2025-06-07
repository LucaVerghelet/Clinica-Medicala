package org.example.dto;

import java.io.Serializable;

public class InregistrareDoctorDTO extends InregistrareUserDTO implements Serializable {
    private String numeSpecializare;


    public InregistrareDoctorDTO(String username, String email, String parola, String numeSpecializare, String nume, String prenume) {
        super(username, email, parola, nume, prenume);
        this.numeSpecializare = numeSpecializare;
    }


    public String getNumeSpecializare() {
        return numeSpecializare;
    }

}
