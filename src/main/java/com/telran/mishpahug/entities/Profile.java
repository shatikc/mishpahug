package com.telran.mishpahug.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@ToString

@Entity
@Table(name="profiles")
public class Profile implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long userId;

    private String email;


    //Here is happening the connection between Profile and Event in format ManyToMany
    @ManyToMany(mappedBy = "subscribers")
    @JsonIgnore
    private Set<Event> events;

    @OneToMany(mappedBy = "owner_email")
    @JsonIgnore
    private Set<Event> owners;

    @ManyToMany(mappedBy = "profilesOfFood")
    @JsonIgnore
    private Set<FoodProfile> foodsOfProfile;

    @ManyToMany(mappedBy = "profilesOfLanguage")
    @JsonIgnore
    private Set<Languages> languagesOfProfile;


    @ManyToMany(mappedBy = "profilesOfHoliday")
    @JsonIgnore
    private Set<Holiday> holidaysOfProfile;

    @OneToMany(mappedBy = "owner_email")
    @JsonIgnore
    private Set<Picture> pictures;

    @OneToMany(mappedBy = "ownerEmail")
    @JsonIgnore
    private Set<Notification> notifications;

    private String password;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String gender;
    private String maritalStatus;
    private String confession;
    private String phoneNumber;
    private String description;
    private double rate;
    private int numberOfVoters;
    private String firebaseToken;
    private String fullName;
    private int age;

    public Profile(String email, Set<Event> events, Set<Event> owners, Set<FoodProfile> foodsOfProfile,
                   Set<Languages> languagesOfProfile, Set<Holiday> holidaysOfProfile, Set<Picture> pictures,
                   Set<Notification> notifications, String password, String firstName, String lastName,
                   LocalDate dateOfBirth, String gender, String maritalStatus, String confession,
                   String phoneNumber, String description, double rate, int numberOfVoters,
                   String firebaseToken, String fullName, int age) {
        this.email = email;
        this.events = events;
        this.owners = owners;
        this.foodsOfProfile = foodsOfProfile;
        this.languagesOfProfile = languagesOfProfile;
        this.holidaysOfProfile = holidaysOfProfile;
        this.pictures = pictures;
        this.notifications = notifications;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.maritalStatus = maritalStatus;
        this.confession = confession;
        this.phoneNumber = phoneNumber;
        this.description = description;
        this.rate = rate;
        this.numberOfVoters = numberOfVoters;
        this.firebaseToken = firebaseToken;
        this.fullName = fullName;
        this.age = age;
    }
}
