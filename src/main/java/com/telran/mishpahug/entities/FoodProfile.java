package com.telran.mishpahug.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

@Entity
@Table(name="food_prof")
public class FoodProfile implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String food;

    @ManyToMany
    @JsonIgnore
    private Set<Profile> profilesOfFood;

}
