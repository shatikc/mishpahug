package com.telran.mishpahug.services.inviteToEvent;

import org.springframework.http.ResponseEntity;

public interface IInviteToEvent {

    ResponseEntity inviteToEvent(String token64, long eventId, long userId);

}
