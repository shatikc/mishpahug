package com.telran.mishpahug.services.userProfile;

import org.springframework.http.ResponseEntity;

public interface IUserProfile {
    ResponseEntity getUser(String token);
}
