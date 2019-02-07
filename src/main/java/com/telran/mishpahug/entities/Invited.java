package com.telran.mishpahug.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@NoArgsConstructor
@Getter
@Setter


@Entity
@Table(name = "invited")
public class Invited implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long invitedId;

     @ManyToOne
     @JsonIgnore
     private Profile userId;

     @ManyToOne
     @JsonIgnore
     private Event eventId;

     private boolean isVoited;

    public Invited(Profile userId, Event eventId, boolean isVoited) {
        this.userId = userId;
        this.eventId = eventId;
        this.isVoited = isVoited;
    }
}
