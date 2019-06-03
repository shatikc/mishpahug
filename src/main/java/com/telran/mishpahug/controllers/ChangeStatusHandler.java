package com.telran.mishpahug.controllers;

import com.telran.mishpahug.api.URLConstants;
import com.telran.mishpahug.services.changeStatus.IChangeEventStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@RestController
public class ChangeStatusHandler {

    @Autowired
    IChangeEventStatus changeService;

    @PutMapping(URLConstants.changeStatusEvent)
    @CrossOrigin
    public ResponseEntity putStatus(HttpServletRequest headers, @PathVariable long eventId){
         String tokenBase64 = headers.getHeader("authorization");
        return changeService.changeEventStatus(tokenBase64,eventId);
    }

}
