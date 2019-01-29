package com.telran.mishpahug.services.listNotifications;

import org.springframework.http.ResponseEntity;

public interface IListNotifications {

    ResponseEntity getList(String token64);
}
