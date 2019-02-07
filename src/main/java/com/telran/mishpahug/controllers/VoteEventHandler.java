package com.telran.mishpahug.controllers;

import com.telran.mishpahug.api.URLConstants;
import com.telran.mishpahug.services.voteEvent.IVoteForEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class VoteEventHandler {

    @Autowired
    IVoteForEvent voteService;

    @PutMapping(URLConstants.voteForEvent)
    @CrossOrigin
    public ResponseEntity putVote(HttpServletRequest headers, @PathVariable long eventId,@PathVariable double voteCount){
        String tokenBase64 = headers.getHeader("authorization");
        return voteService.voteForEvent(tokenBase64,eventId,voteCount);
    }

}
