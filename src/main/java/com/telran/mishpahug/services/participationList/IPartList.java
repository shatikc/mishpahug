package com.telran.mishpahug.services.participationList;

import org.springframework.http.ResponseEntity;

public interface IPartList {

    ResponseEntity getListOfParticipations(String token64);
}
