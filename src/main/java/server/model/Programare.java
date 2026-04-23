package server.model;

import common.dto.ProgramareDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import org.hibernate.annotations.CurrentTimestamp;
import org.hibernate.generator.EventType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


@Entity
@Table(name = "Programare")
@NamedQuery(name = "Programare.findAll", query = "SELECT p FROM Programare p")

public class Programare {

    @ManyToOne
    @JoinColumn(name = "Pacient_cnp", referencedColumnName = "cnp")
    private Pacient pacient;

    @ManyToOne
    @JoinColumn(name = "Doctor_id", referencedColumnName = "id")
    private Doctor doctor;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column (name = "data")
    private LocalDate data;

    @Column (name = "ora")
    private LocalTime ora;

    @Column (name = "comentariu")
    private String comentariu;

    @Column (name = "createdAt")
    @CurrentTimestamp(event = EventType.INSERT)
    private LocalDateTime createdAt;

    @Column (name = "updatedAt")
    @CurrentTimestamp(event = {EventType.INSERT, EventType.UPDATE})
    private LocalDateTime updatedAt;

    public Programare() {
    }

    public Programare(LocalDate data, LocalTime ora, Pacient pacient, Doctor doctor, String comentariu) {
        this.data = data;
        this.ora = ora;
        this.pacient = pacient;
        this.doctor = doctor;
        this.comentariu = comentariu;
    }

    public ProgramareDTO getProgramareDTO() {
        ProgramareDTO programareDTO = new ProgramareDTO();
        programareDTO.setEmailPacient(pacient.getUserProfile().getEmail());
        programareDTO.setNumePacient(pacient.getNume() + " " + pacient.getPrenume());
        programareDTO.setEmailDoctor(doctor.getUserProfile().getEmail());
        programareDTO.setNumeDocotor(doctor.getNume() + " " + doctor.getPrenume());
        programareDTO.setData(data);
        programareDTO.setOra(ora);
        programareDTO.setComentariu(comentariu);
        return programareDTO;
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

    public void setData(LocalDate data) {
        this.data = data;
    }

    public void setOra(LocalTime ora) {
        this.ora = ora;
    }

    public void setComentariu(String comentariu) {
        this.comentariu = comentariu;
    }
}


