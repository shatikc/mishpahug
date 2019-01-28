package com.telran.mishpahug.controllers;

import com.telran.mishpahug.api.RequestsDTO.TokenDTO;
import com.telran.mishpahug.api.URLConstants;
import com.telran.mishpahug.services.addFirebase.IAddFirebase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class AddFirebaseHandler {

    @Autowired
    IAddFirebase addTokenService;


    @PostMapping(URLConstants.addFirebaseToken)
    @CrossOrigin
    public ResponseEntity addFirebaseToken(HttpServletRequest headers, @RequestBody TokenDTO data){
        String tokenBase64 = headers.getHeader("authorization");
        return addTokenService.addToken(tokenBase64, data);
    }
}
