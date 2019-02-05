package com.telran.mishpahug.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Comparator;

@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name="picture")
public class Picture implements Serializable, Comparator<Picture> {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long pictureId;

    private String url;
    private boolean isAvatar;

    @ManyToOne
    @JsonIgnore
    private Profile owner_email;

    public Picture(String url, boolean isAvatar, Profile owner_email) {
        this.url = url;
        this.isAvatar = isAvatar;
        this.owner_email = owner_email;
    }

    @Override
    public int compare(Picture picA, Picture picB) {
        return picA.isAvatar==picB.isAvatar?0:(picA.isAvatar ? -1 : 1);
    }

    @Override
    public String toString() {
        return "url='" + url;
    }
}
