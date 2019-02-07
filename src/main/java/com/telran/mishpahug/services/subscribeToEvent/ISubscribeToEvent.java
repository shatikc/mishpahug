package com.telran.mishpahug.services.subscribeToEvent;

import org.springframework.http.ResponseEntity;

public interface ISubscribeToEvent {

    ResponseEntity subscribeEvent(String token64, long eventId);

}
