package com.telran.mishpahug.services.subscribedEventInfo;

import org.springframework.http.ResponseEntity;

public interface ISubscribedEventInfo {

    ResponseEntity getSubscribedEventInfo(String token64, long eventId);

}
