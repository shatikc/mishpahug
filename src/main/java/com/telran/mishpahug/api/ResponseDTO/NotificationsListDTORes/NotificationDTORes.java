package com.telran.mishpahug.api.ResponseDTO.NotificationsListDTORes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class NotificationDTORes implements Serializable {
    private long notificationId;
    private String title;
    private String message;
    private LocalDate date;
    private String type;
    private boolean isRead;
    private long eventId;

    @Override
    public String toString() {
        return "NotificationDTORes{" +
                "notificationId=" + notificationId +
                ", title='" + title + '\'' +
                ", message='" + message + '\'' +
                ", date=" + date +
                ", type='" + type + '\'' +
                ", isRead=" + isRead +
                ", eventId=" + eventId +
                '}';
    }
}


