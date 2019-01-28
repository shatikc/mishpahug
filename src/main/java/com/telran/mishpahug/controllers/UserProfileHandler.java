package com.telran.mishpahug.controllers;

import com.telran.mishpahug.api.URLConstants;
import com.telran.mishpahug.services.userProfile.IUserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserProfileHandler {
    @Autowired
    IUserProfile userService;

    @GetMapping(URLConstants.getUser)
    @CrossOrigin
    public ResponseEntity getUserProfile(HttpServletRequest headers){
        String tokenBase64 = headers.getHeader("authorization");
        return userService.getUser(tokenBase64);
    }
}
