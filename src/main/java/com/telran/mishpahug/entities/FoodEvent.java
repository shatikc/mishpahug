package com.telran.mishpahug.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

@Entity
@Table(name="food_event")
public class FoodEvent implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private String food;

    @ManyToMany
    Set<Event> eventsOfFood;


}
