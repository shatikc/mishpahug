package com.telran.mishpahug.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
@Table(name="picture")
public class Picture implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private String url;
    private boolean isAvatar;

    @ManyToOne
    Profile owner_email;
}
