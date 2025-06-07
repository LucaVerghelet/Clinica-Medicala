package server.model;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import org.hibernate.annotations.CurrentTimestamp;
import org.hibernate.generator.EventType;

import java.time.LocalDateTime;


@Entity
@Table(name = "UserProfile")
@NamedQuery(name = "UserProfile.findAll", query = "SELECT u FROM UserProfile u")

public class UserProfile {


    @Column(name = "username")
    private String username;

    @Id
    @Column (name = "email")
    private String email;

    @Column (name = "password")
    private String password;


    @Column (name = "createdAt")
    @CurrentTimestamp(event = EventType.INSERT)
    private LocalDateTime createdAt;

    @Column (name = "updatedAt")
    @CurrentTimestamp(event = {EventType.INSERT, EventType.UPDATE})
    private LocalDateTime updatedAt;

    public UserProfile() {
    }


    public UserProfile(String username, String email, String parola) {
        this.username = username;
        this.email = email;
        this.password = parola;
//        this.updatedAt = LocalDateTime.now();
//        this.createdAt = LocalDateTime.now();
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}

