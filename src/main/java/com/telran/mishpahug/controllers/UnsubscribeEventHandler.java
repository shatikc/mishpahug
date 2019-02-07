package com.telran.mishpahug.controllers;

import com.telran.mishpahug.api.URLConstants;
import com.telran.mishpahug.services.unsubscribeFromEvent.IUnsubscribeFromEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UnsubscribeEventHandler {

    @Autowired
    IUnsubscribeFromEvent unsubscribeService;

    @PutMapping(URLConstants.unsubscribeFromEvent)
    @CrossOrigin
    public ResponseEntity unsubscribe(HttpServletRequest headers, @PathVariable long eventId){
         String tokenBase64 = headers.getHeader("authorization");
        return unsubscribeService.unsubscribeFromEvent(tokenBase64,eventId);
    }

}
