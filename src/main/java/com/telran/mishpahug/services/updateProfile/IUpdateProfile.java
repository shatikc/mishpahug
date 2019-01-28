package com.telran.mishpahug.services.updateProfile;

import com.telran.mishpahug.api.RequestsDTO.UpdateProfileDTO;
import org.springframework.http.ResponseEntity;

public interface IUpdateProfile {

    ResponseEntity updateProfile(String token, UpdateProfileDTO data);
}
