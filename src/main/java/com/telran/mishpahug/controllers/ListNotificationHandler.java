package com.telran.mishpahug.controllers;

import com.telran.mishpahug.api.URLConstants;
import com.telran.mishpahug.services.listNotifications.IListNotifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class ListNotificationHandler {

    @Autowired
    IListNotifications listService;

    @GetMapping(URLConstants.getListNotifications)
    @CrossOrigin
    public ResponseEntity getListNotifications(HttpServletRequest headers){
        String tokenBase64 = headers.getHeader("authorization");
        return listService.getList(tokenBase64);
    }

}
