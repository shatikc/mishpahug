package com.telran.mishpahug.controllers;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.telran.mishpahug.api.URLConstants;
import com.telran.mishpahug.services.registration.IRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@RestController
public class RegUserHandler {
    @Autowired
    IRegistration regService;

    @PostMapping(URLConstants.registrationUser)
    @CrossOrigin
    public ResponseEntity registrationUser(HttpServletRequest headers){
        String token = headers.getHeader("authorization");
        return regService.addNewUser(token);
    }

}
