package com.telran.mishpahug.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
@Table(name="notifications")
public class Notification implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long notificationId;

    private String title;
    private String message;
    private LocalDate date;
    private String type;
    private boolean isRead;
    private long eventId;
    private String profile_email;

    @ManyToOne
    Profile ownerOfNotification;
}
