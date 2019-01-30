package com.telran.mishpahug.services.notifIsRead;

import com.telran.mishpahug.entities.Profile;
import org.springframework.http.ResponseEntity;

public interface INotifIsRead {

    ResponseEntity putNotificationIsRead(String token64, long notId);
}
