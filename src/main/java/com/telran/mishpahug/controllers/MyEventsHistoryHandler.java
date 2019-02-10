package com.telran.mishpahug.controllers;

import com.telran.mishpahug.api.URLConstants;
import com.telran.mishpahug.services.myEventsHistory.IMyEventsHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class MyEventsHistoryHandler {

    @Autowired
    IMyEventsHistory historyListService;

    @GetMapping(URLConstants.myEventsHistory)
    @CrossOrigin
    public ResponseEntity getHistory(HttpServletRequest headers){
         String tokenBase64 = headers.getHeader("authorization");
        return historyListService.getMyEventsHistoryList(tokenBase64);
    }

}
