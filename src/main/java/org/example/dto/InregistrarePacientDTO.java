package org.example.dto;

import java.io.Serializable;

public class InregistrarePacientDTO extends InregistrareUserDTO implements Serializable {
    private String cnp;
    private String alergii;

    public InregistrarePacientDTO(String username, String email, String parola, String cnp, String alergii, String nume, String prenume) {
        super(username, email, parola, nume, prenume);
        this.cnp = cnp;
        this.alergii = alergii;
    }

    public String getcnp() {
        return cnp;
    }

    public String getAlergii() {
        return alergii;
    }

}
