package com.telran.mishpahug.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
@Table(name="event")
public class Event implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long eventId;

    @ManyToMany(mappedBy = "events")
    @JsonIgnore
    private Set<Profile> subscribers;

    @ManyToOne
    Profile owner_email;

    @ManyToMany(mappedBy = "eventsOfFood")
    @JsonIgnore
    Set<FoodEvent> foodsOfEvent;

    private String title;
    private String holiday;
    private String confession;
    private LocalDate date;
    private LocalTime time;
    private int duration;
    private String description;
    private String status;
    private String city;
    private String place_id;
    private double lat;
    private double lng;

}
