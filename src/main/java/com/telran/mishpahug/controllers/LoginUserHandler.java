package com.telran.mishpahug.controllers;

import com.telran.mishpahug.api.URLConstants;
import com.telran.mishpahug.services.login.ILogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class LoginUserHandler {

    @Autowired
    ILogin logService;

    @PostMapping(URLConstants.loginUser)
    @CrossOrigin
    public ResponseEntity loginUser(HttpServletRequest headers){
        String token = headers.getHeader("authorization");
        return logService.loginUser(token);
    }
}
