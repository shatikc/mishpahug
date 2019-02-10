package com.telran.mishpahug.services.eventsForCalendar;

import org.springframework.http.ResponseEntity;

public interface IEventsForCalendar {

    ResponseEntity getEventsForCalendar(String token64, int mount);

}
