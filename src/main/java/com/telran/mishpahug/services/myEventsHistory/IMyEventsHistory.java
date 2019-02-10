package com.telran.mishpahug.services.myEventsHistory;

import org.springframework.http.ResponseEntity;

public interface IMyEventsHistory {

    ResponseEntity getMyEventsHistoryList(String token64);

}
