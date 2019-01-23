package com.telran.mishpahug.api.ResponseDTO.NotificationsListDTORes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter

public class Notification implements Serializable {
    private long notificationId;
    private String title;
    private String message;
    private String date;
    private String type;
    private boolean isRead;
    private long eventId;
}
