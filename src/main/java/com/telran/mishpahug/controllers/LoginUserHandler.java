package com.telran.mishpahug.controllers;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.telran.mishpahug.api.URLConstants;
import com.telran.mishpahug.services.login.ILogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginUserHandler {

    @Autowired
    ILogin logService;

    @PostMapping(URLConstants.loginUser)
    @CrossOrigin
    public ResponseEntity loginUser(@JsonFormat HttpEntity object){
        String headers = object.getHeaders().toString();
        return logService.loginUser(headers);
    }
}
