package com.telran.mishpahug.services.myEventList;

import org.springframework.http.ResponseEntity;

public interface IMyEventList {

    ResponseEntity getMyEventList(String token64);

}
