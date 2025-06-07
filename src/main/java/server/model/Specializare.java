package server.model;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import org.hibernate.annotations.CurrentTimestamp;
import org.hibernate.generator.EventType;

import java.time.LocalDateTime;


@Entity
@Table(name = "Specializare")
@NamedQuery(name = "Specializare.findAll", query = "SELECT s FROM Specializare s")

public class Specializare {
    @Id
    @Column(name = "numeSpecializare")
    private String numeSpecializare;

    @Column(name = "necesitaRecomandare")
    private boolean necesitaRecomandare;

    @Column (name = "createdAt")
    @CurrentTimestamp(event = EventType.INSERT)
    private LocalDateTime createdAt;

    @Column (name = "updatedAt")
    @CurrentTimestamp(event = {EventType.INSERT, EventType.UPDATE})
    private LocalDateTime updatedAt;

    public Specializare() {
    }

    public Specializare( String nume, String prenume, Integer CNP, String alergii) {
        this.numeSpecializare = numeSpecializare;
    }

    public static Specializare findByNume(String nume, EntityManager em) {
        Specializare obj = em.find(Specializare.class, nume);
        return obj;
    }

    public String getNumeSpecializare() {
        return numeSpecializare;
    }

    public boolean isNecesitaRecomandare() {
        return necesitaRecomandare;
    }
}


