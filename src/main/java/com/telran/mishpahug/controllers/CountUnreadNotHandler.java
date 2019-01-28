package com.telran.mishpahug.controllers;

import com.telran.mishpahug.api.URLConstants;
import com.telran.mishpahug.services.countUnreadNot.ICountUnreadNot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class CountUnreadNotHandler {

    @Autowired
    ICountUnreadNot countService;

    @GetMapping(URLConstants.getCountUnreadNotifications)
    @CrossOrigin
    public ResponseEntity getCount(HttpServletRequest headers){
        String token64 = headers.getHeader("authorization");
        return countService.getCountUnreadNotifications(token64);
    }

}
