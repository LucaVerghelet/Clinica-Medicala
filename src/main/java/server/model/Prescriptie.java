package server.model;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import org.hibernate.annotations.CurrentTimestamp;
import org.hibernate.generator.EventType;

import java.time.LocalDateTime;


@Entity
@Table(name = "Prescriptie")
@NamedQuery(name = "Prescriptie.findAll", query = "SELECT p FROM Prescriptie p")

public class Prescriptie {

    @OneToOne
    @JoinColumn(name = "Programare_id", referencedColumnName = "id")
    private Programare programare;

    @Column(name = "text_prescriptie")
    private String text_prescriptie;

    @Id
    @Column(name = "id")
    private Integer id;

    @Column (name = "createdAt")
    @CurrentTimestamp(event = EventType.INSERT)
    private LocalDateTime createdAt;

    @Column (name = "updatedAt")
    @CurrentTimestamp(event = {EventType.INSERT, EventType.UPDATE})
    private LocalDateTime updatedAt;

    public Prescriptie() {
    }

    public Prescriptie(Integer id, String text_prescriptie) {

        this.text_prescriptie = text_prescriptie;
        this.id = id;

    }

    public void setProgramare(Programare programare) {
        this.programare = programare;
    }

    public void setText_prescriptie(String text_prescriptie) {
        this.text_prescriptie = text_prescriptie;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}


