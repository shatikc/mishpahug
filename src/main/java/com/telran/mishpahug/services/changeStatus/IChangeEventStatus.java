package com.telran.mishpahug.services.changeStatus;

import org.springframework.http.ResponseEntity;

public interface IChangeEventStatus {

    ResponseEntity changeEventStatus(String token64, long eventId);

}
