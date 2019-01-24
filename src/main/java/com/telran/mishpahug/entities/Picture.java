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
import java.util.Comparator;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
@Table(name="picture")
public class Picture implements Serializable, Comparator<Picture> {
    private static final long serialVersionUID = 1L;
    @Id
    private String url;
    private boolean isAvatar;

    @ManyToOne
    Profile owner_email;


    @Override
    public int compare(Picture picA, Picture picB) {
        return picA.isAvatar==picB.isAvatar?0:(picA.isAvatar ? -1 : 1);
    }
}
