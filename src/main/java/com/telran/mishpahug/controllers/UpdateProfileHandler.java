package com.telran.mishpahug.controllers;
import com.telran.mishpahug.api.RequestsDTO.UpdateProfileDTO;
import com.telran.mishpahug.api.URLConstants;
import com.telran.mishpahug.services.updateProfile.IUpdateProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;

@RestController
public class UpdateProfileHandler {

    @Autowired
    IUpdateProfile updateService;

    @PostMapping(value = URLConstants.updateUserProfile)
    @CrossOrigin
    public ResponseEntity updateNewProfile(HttpServletRequest headers,@RequestBody UpdateProfileDTO data){
        String token = headers.getHeader("authorization");
        return updateService.updateProfile(token, data);
    }
}
