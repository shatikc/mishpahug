package com.telran.mishpahug.controllers;


import com.telran.mishpahug.api.URLConstants;
import com.telran.mishpahug.services.myEventInfo.IMyEventInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

// IS NOT FINISHED!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

@RestController
public class MyEventInfoHandler {

    @Autowired
    IMyEventInfo myEventService;

    @GetMapping(URLConstants.getMyEventInfo)
    @CrossOrigin
    public ResponseEntity getEvent(HttpServletRequest headers, @PathVariable long eventId ){
        String tokenBase64 = headers.getHeader("authorization");
        return myEventService.getEventInfo(tokenBase64,eventId);
    }
}
