package com.telran.mishpahug.services.myEventInfo;

import org.springframework.http.ResponseEntity;

public interface IMyEventInfo {

    ResponseEntity getEventInfo(String token64, long eventId);
}
