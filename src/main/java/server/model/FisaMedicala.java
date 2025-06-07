package server.model;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import org.hibernate.annotations.CurrentTimestamp;
import org.hibernate.generator.EventType;

import java.time.LocalDateTime;


@Entity
@Table(name = "FisaMedicala")
@NamedQuery(name = "FisaMedicala.findAll", query = "SELECT f FROM FisaMedicala f")

public class FisaMedicala {

    @ManyToOne
    @JoinColumn(name = "Pacient_cnp", referencedColumnName = "cnp")
    private Pacient pacient;

    @ManyToOne
    @JoinColumn(name = "Doctor_id", referencedColumnName = "id")
    private Doctor doctor;

    @Id
    @Column(name = "id")
    private Integer id;

    @Column (name = "comentariu")
    private String comentariu;

    @Column (name = "diagnostic")
    private String diagnostic;

    @Column (name = "tratanment")
    private String tratament;

    @OneToOne
    @JoinColumn(name = "Prescriptie_id", referencedColumnName = "id")
    private Prescriptie prescriptie;

    @Column (name = "createdAt")
    @CurrentTimestamp(event = EventType.INSERT)
    private LocalDateTime createdAt;

    @Column (name = "updatedAt")
    @CurrentTimestamp(event = {EventType.INSERT, EventType.UPDATE})
    private LocalDateTime updatedAt;

    public FisaMedicala() {
    }

    public FisaMedicala(Pacient pacient, Doctor doctor, String comentariu, String diagnostic, String tratament, Prescriptie prescriptie) {
        this.pacient = pacient;
        this.doctor = doctor;
        this.comentariu = comentariu;
        this.diagnostic = diagnostic;
        this.tratament = tratament;
        this.prescriptie = prescriptie;
    }

    public void setPacient(Pacient pacient) {
        this.pacient = pacient;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setComentariu(String comentariu) {
        this.comentariu = comentariu;
    }

    public void setDiagnostic(String diagnostic) {
        this.diagnostic = diagnostic;
    }

    public void setTratament(String tratament) {
        this.tratament = tratament;
    }

    public void setPrescriptie(Prescriptie prescriptie) {
        this.prescriptie = prescriptie;
    }
}


