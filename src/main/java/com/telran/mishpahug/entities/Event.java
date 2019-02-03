package com.telran.mishpahug.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;


@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name="event")
public class Event implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long eventId;

    @ManyToMany
    @JsonIgnore
    private Set<Profile> subscribers;

    @ManyToOne
    Profile owner_email;

    @ManyToMany(mappedBy = "eventsOfFood")
    @JsonIgnore
    Set<FoodEvent> foodsOfEvent;

    @OneToMany(mappedBy = "eventId")
    @JsonIgnore
    Set<Invited> invited;

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

    public Event(Set<Profile> subscribers, Profile owner_email, Set<FoodEvent> foodsOfEvent, String title,
                 String holiday, String confession, LocalDate date, LocalTime time, int duration,
                 String description, String status, String city, String place_id, double lat, double lng) {
        this.subscribers = subscribers;
        this.owner_email = owner_email;
        this.foodsOfEvent = foodsOfEvent;
        this.title = title;
        this.holiday = holiday;
        this.confession = confession;
        this.date = date;
        this.time = time;
        this.duration = duration;
        this.description = description;
        this.status = status;
        this.city = city;
        this.place_id = place_id;
        this.lat = lat;
        this.lng = lng;
    }
}
