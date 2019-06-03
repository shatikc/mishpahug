package com.telran.mishpahug.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
@Table(name="event")
public class Event implements Serializable, Comparator<Event> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long eventId;

    @ManyToMany(fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Profile> subscribers;

    @ManyToOne
    private Profile owner_email;

    @ManyToMany(mappedBy = "eventsOfFood")
    @JsonIgnore
    private Set<FoodEvent> foodsOfEvent;

    @OneToMany(mappedBy = "eventId")
    @JsonIgnore
    private Set<Invited> invited;

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

    @Override
    public int compare(Event a, Event b) {
        return a.date.equals(b.date)?0:a.date.isBefore(b.date)?-1:1;
    }

    @Override
    public String toString() {
        return "Event{" +
                "eventId=" + eventId +
                ", title='" + title + '\'' +
                ", holiday='" + holiday + '\'' +
                ", date=" + date +
                ", time=" + time +
                ", duration=" + duration +
                ", status='" + status + '\'' +
                '}';
    }
}
