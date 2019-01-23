package com.telran.mishpahug.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
@Table(name="profiles")
public class Profile implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String email;

    @ManyToMany
    private Set<Event> events;

    @OneToMany(mappedBy = "owner_email")
    @JsonIgnore
    Set<Event> owners;

    @ManyToMany(mappedBy = "profilesOfFood")
    @JsonIgnore
    Set<FoodProfile> foodsOfProfile;

    @ManyToMany(mappedBy = "profilesOfLanguage")
    @JsonIgnore
    Set<Languages> languagesOfProfile;

    @OneToMany(mappedBy = "owner_email")
    @JsonIgnore
    Set<Picture> pictures;

    @OneToMany(mappedBy = "ownerOfNotification")
    @JsonIgnore
    Set<Notification> notifications;

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
