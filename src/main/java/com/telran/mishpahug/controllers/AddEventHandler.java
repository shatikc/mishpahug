package com.telran.mishpahug.controllers;

import com.telran.mishpahug.api.RequestsDTO.AddEventDTO.AddEventDTO;
import com.telran.mishpahug.api.URLConstants;
import com.telran.mishpahug.services.event.IAddEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class AddEventHandler {

    @Autowired
    IAddEvent addEventService;

    @PostMapping(URLConstants.addEvent)
    @CrossOrigin
    public ResponseEntity addEvent(HttpServletRequest headers, @RequestBody AddEventDTO event){
        String tokenBase64 = headers.getHeader("authorization");
        return addEventService.createNewEvent(tokenBase64,event);
    }

}
