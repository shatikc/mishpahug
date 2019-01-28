package com.telran.mishpahug.controllers;

import com.telran.mishpahug.api.RequestsDTO.TokenDTO;
import com.telran.mishpahug.api.URLConstants;
import com.telran.mishpahug.services.deleteFirebase.IDeleteFirebase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class DeleteFirebaseHandler {

    @Autowired
    IDeleteFirebase deleteTokenService;

    @DeleteMapping(URLConstants.deleteFirebaseToken)
    @CrossOrigin
    public ResponseEntity deleteFirebaseToken(HttpServletRequest headers, @RequestBody TokenDTO data){
        String tokenBase64 = headers.getHeader("authorization");
        return deleteTokenService.deleteToken(tokenBase64, data);
    }
}
