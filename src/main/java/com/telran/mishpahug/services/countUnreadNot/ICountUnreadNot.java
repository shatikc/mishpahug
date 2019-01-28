package com.telran.mishpahug.services.countUnreadNot;

import org.springframework.http.ResponseEntity;

public interface ICountUnreadNot {

    ResponseEntity getCountUnreadNotifications(String token64);
}
