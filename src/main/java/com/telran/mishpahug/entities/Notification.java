package com.telran.mishpahug.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.telran.mishpahug.api.ResponseDTO.NotificationsListDTORes.NotificationDTORes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Comparator;

@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name="notifications")
public class Notification implements Serializable, Comparator<Notification> {
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
    /*private String profile_email;*/

    @ManyToOne
    @JsonIgnore
    private Profile ownerEmail;
    /*private Profile ownerOfNotification;*/

    public Notification(String title, String message, LocalDate date, String type,
                        boolean isRead, long eventId, Profile ownerEmail) {
        this.title = title;
        this.message = message;
        this.date = date;
        this.type = type;
        this.isRead = isRead;
        this.eventId = eventId;
        this.ownerEmail = ownerEmail;
    }

    @Override
    public int compare(Notification a, Notification b) {
        if(a.isRead&&!b.isRead){return 1;}
        if(!a.isRead && b.isRead){return -1;}
        return a.date.equals(b.date)?0:a.date.isBefore(b.date)?1:-1;
    }

    public NotificationDTORes transformToDTORes(){
        return new NotificationDTORes(this.getNotificationId(),this.getTitle(),
                this.getMessage(),this.getDate(),this.getType(),
                this.isRead(),this.getEventId());
    }

}
