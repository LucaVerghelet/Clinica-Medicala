package common.dto;

import java.io.Serializable;

public class LoginDTO implements Serializable {
    private String email;
    private String parola;

    public LoginDTO(String email, String parola) {
        this.email = email;
        this.parola = parola;
    }

    public String getEmail() {
        return email;
    }

    public String getParola() {
        return parola;
    }
}
