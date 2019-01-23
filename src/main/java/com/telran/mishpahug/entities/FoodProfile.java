package com.telran.mishpahug.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    Set<Profile> profilesOfFood;
}
