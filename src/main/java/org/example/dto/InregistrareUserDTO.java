package org.example.dto;

import java.io.Serializable;

public class InregistrareUserDTO implements Serializable {
    private String username;
    private String email;
    private String parola;
    private String nume;
    private String prenume;

    public InregistrareUserDTO(String username, String email, String parola, String nume, String prenume) {
        this.username = username;
        this.email = email;
        this.parola = parola;
        this.nume = nume;
        this.prenume = prenume;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getParola() {
        return parola;
    }

    public String getNume() {
        return nume;
    }

    public String getPrenume() {
        return prenume;
    }
}
