package com.telran.mishpahug.controllers;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.telran.mishpahug.api.URLConstants;
import com.telran.mishpahug.model.IHugAddUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RegUserHandler {
    @Autowired
    IHugAddUser hugCrudModel;

    @PostMapping(URLConstants.registrationUser)
    @CrossOrigin
    public ResponseEntity registrationUser(@JsonFormat HttpEntity object){
        String headers = object.getHeaders().toString();
        return hugCrudModel.addNewUser(headers);
    }


}
