package com.telran.mishpahug.services.unsubscribeFromEvent;

import org.springframework.http.ResponseEntity;

public interface IUnsubscribeFromEvent {

    ResponseEntity unsubscribeFromEvent(String token64, long eventId);
}
