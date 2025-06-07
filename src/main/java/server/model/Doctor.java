package server.model;

import common.dto.DoctorDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import org.hibernate.annotations.CurrentTimestamp;
import org.hibernate.generator.EventType;

import java.time.LocalDateTime;


@Entity
@Table(name = "Doctor")
@NamedQuery(name = "Doctor.findAll", query = "SELECT d FROM Doctor d")

public class Doctor {

    @OneToOne
    @JoinColumn(name = "UserProfile_email", referencedColumnName = "email")
    private UserProfile userProfile;

    @Column(name = "nume")
    private String nume;

    @Column(name = "prenume")
    private String prenume;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "Specializare_numeSpecializare", referencedColumnName = "numeSpecializare")
    private Specializare specializare;

    @Column (name = "createdAt")
    @CurrentTimestamp(event = EventType.INSERT)
    private LocalDateTime createdAt;

    @Column (name = "updatedAt")
    @CurrentTimestamp(event = {EventType.INSERT, EventType.UPDATE})
    private LocalDateTime updatedAt;

    public Doctor() {
    }

    public Doctor( String nume, String prenume, Specializare specializare, UserProfile userProfile) {
        this.userProfile = userProfile;
        this.nume = nume;
        this.prenume = prenume;
        this.specializare = specializare;
    }
    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }


    public void setSpecializare(Specializare specializare) {
        this.specializare = specializare;
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

    public DoctorDTO toDto() {
        return new DoctorDTO(id, nume, userProfile.getEmail(), specializare.getNumeSpecializare(), specializare.isNecesitaRecomandare());
    }
}


