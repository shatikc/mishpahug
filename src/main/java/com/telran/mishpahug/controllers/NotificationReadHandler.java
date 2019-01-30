package com.telran.mishpahug.controllers;

import com.telran.mishpahug.api.URLConstants;
import com.telran.mishpahug.services.notifIsRead.INotifIsRead;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class NotificationReadHandler {

    @Autowired
    INotifIsRead notIsReadService;

    @PutMapping(URLConstants.notificationIsRead)
    @CrossOrigin
    public ResponseEntity getNotification(HttpServletRequest headers, @PathVariable long notificationId){
         String tokenBase64 = headers.getHeader("authorization");
         return notIsReadService.putNotificationIsRead(tokenBase64,notificationId);
    }

}
