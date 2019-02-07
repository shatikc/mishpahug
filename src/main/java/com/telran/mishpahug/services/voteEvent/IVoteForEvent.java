package com.telran.mishpahug.services.voteEvent;

import org.springframework.http.ResponseEntity;

public interface IVoteForEvent {

    ResponseEntity voteForEvent(String token64, long eventId, double vote);
}
