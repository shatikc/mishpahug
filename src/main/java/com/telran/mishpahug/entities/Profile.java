package com.telran.mishpahug.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

@Entity
@Table(name="profiles")
public class Profile implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String email;

    @ManyToMany
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

}
