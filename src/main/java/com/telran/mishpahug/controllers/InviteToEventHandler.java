package com.telran.mishpahug.controllers;

import com.telran.mishpahug.api.URLConstants;
import com.telran.mishpahug.services.inviteToEvent.IInviteToEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class InviteToEventHandler {

    @Autowired
    IInviteToEvent inviteService;

    @PutMapping(URLConstants.inviteToEvent)
    @CrossOrigin
    public ResponseEntity invite(HttpServletRequest headers, @PathVariable long eventId,@PathVariable long userId){
        String tokenBase64 = headers.getHeader("authorization");
        return inviteService.inviteToEvent(tokenBase64,eventId,userId);
    }
}
