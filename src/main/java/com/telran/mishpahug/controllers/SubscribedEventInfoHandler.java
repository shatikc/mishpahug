package com.telran.mishpahug.controllers;

import com.telran.mishpahug.api.URLConstants;
import com.telran.mishpahug.services.subscribedEventInfo.ISubscribedEventInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class SubscribedEventInfoHandler {

    @Autowired
    ISubscribedEventInfo subscribedEventService;

    @GetMapping(URLConstants.subscribedEventInfo)
    @CrossOrigin
    public ResponseEntity getInfo(HttpServletRequest headers, @PathVariable long eventId){
        String token = headers.getHeader("authorization");
        return subscribedEventService.getSubscribedEventInfo(token,eventId);
    }

}
