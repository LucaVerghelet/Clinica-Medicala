package common.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public class ProgramareDTO implements Serializable {
    private String emailPacient;
    private String numePacient;
    private String emailDoctor;
    private String numeDocotor;
    private String numeSpecializareDoctor;
    private LocalDate data;
    private LocalTime ora;
    private String comentariu;

    public ProgramareDTO() {
    }

    public ProgramareDTO(String emailPacient, String numePacient, String emailDoctor, String numeDocotor, String numeSpecializareDoctor, LocalDate data, LocalTime ora, String comentariu) {
        this.emailPacient = emailPacient;
        this.numePacient = numePacient;
        this.emailDoctor = emailDoctor;
        this.numeDocotor = numeDocotor;
        this.numeSpecializareDoctor = numeSpecializareDoctor;
        this.data = data;
        this.ora = ora;
        this.comentariu = comentariu;
    }

    public String toStringPentruDoctor() {
        return  data +
                " - " + ora +
                "\n\tPacient: " + numePacient +
                "(" + emailPacient + ")" +
                "\n\tComentariu: " + comentariu;
    }

    public String toStringPentruPacient() {
        return data +
                " - " + ora +
                "\n\tDoctor: " + numeDocotor +
                "(" + emailDoctor + ")" +
                "\n\tSpecializare: " + numeSpecializareDoctor;
    }

    public String getEmailPacient() {
        return emailPacient;
    }

    public void setEmailPacient(String emailPacient) {
        this.emailPacient = emailPacient;
    }

    public String getNumePacient() {
        return numePacient;
    }

    public void setNumePacient(String numePacient) {
        this.numePacient = numePacient;
    }

    public String getEmailDoctor() {
        return emailDoctor;
    }

    public void setEmailDoctor(String emailDoctor) {
        this.emailDoctor = emailDoctor;
    }

    public String getNumeDocotor() {
        return numeDocotor;
    }

    public void setNumeDocotor(String numeDocotor) {
        this.numeDocotor = numeDocotor;
    }

    public String getNumeSpecializareDoctor() {
        return numeSpecializareDoctor;
    }

    public void setNumeSpecializareDoctor(String numeSpecializareDoctor) {
        this.numeSpecializareDoctor = numeSpecializareDoctor;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getOra() {
        return ora;
    }

    public void setOra(LocalTime ora) {
        this.ora = ora;
    }

    public String getComentariu() {
        return comentariu;
    }

    public void setComentariu(String comentariu) {
        this.comentariu = comentariu;
    }
}
