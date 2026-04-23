package server.model;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import org.hibernate.annotations.CurrentTimestamp;
import org.hibernate.generator.EventType;

import java.time.LocalDateTime;


@Entity
@Table(name = "Pacient")
@NamedQuery(name = "Pacient.findAll", query = "SELECT p FROM Pacient p")

public class Pacient {

    @OneToOne
    @JoinColumn(name = "UserProfile_email", referencedColumnName = "email")
    private UserProfile userProfile;

    @Column(name = "nume")
    private String nume;

    @Column(name = "prenume")
    private String prenume;

    @Id
    @Column(name = "cnp")
    private String cnp;

    @Column(name = "alergii")
    private String alergii;

    @Column (name = "createdAt")
    @CurrentTimestamp(event = EventType.INSERT)
    private LocalDateTime createdAt;

    @Column (name = "updatedAt")
    @CurrentTimestamp(event = {EventType.INSERT, EventType.UPDATE})
    private LocalDateTime updatedAt;

    public Pacient() {
    }

    public Pacient( String nume, String prenume, String cnp, String alergii, UserProfile userProfile) {
        this.userProfile = userProfile;
        this.nume = nume;
        this.prenume = prenume;
        this.cnp = cnp;
        this.alergii = alergii;
    }
    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public void setCNP(Integer CNP) {
        this.cnp = cnp;
    }

    public void setAlergii(String alergii) {
        this.alergii = alergii;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public String getNume() {
        return nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public String getCnp() {
        return cnp;
    }

    public String getAlergii() {
        return alergii;
    }
}


