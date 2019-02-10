package com.telran.mishpahug.controllers;

import com.telran.mishpahug.api.URLConstants;
import com.telran.mishpahug.services.eventsForCalendar.IEventsForCalendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class EventListCalendarHandler {

    @Autowired
    IEventsForCalendar calendarService;

    @GetMapping(URLConstants.eventListForCalendar)
    @CrossOrigin
    public ResponseEntity getEvents(HttpServletRequest headers, @PathVariable int month){
        String tokenBase64 = headers.getHeader("authorization");
        return calendarService.getEventsForCalendar(tokenBase64,month);
    }

}
