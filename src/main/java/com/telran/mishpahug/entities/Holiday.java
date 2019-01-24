package com.telran.mishpahug.entities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
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
@Table(name="holiday")
public class Holiday implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private String holiday;

    @ManyToMany
    Set<Profile> profilesOfHoliday;
}
