package com.telran.mishpahug.controllers;

import com.telran.mishpahug.api.URLConstants;
import com.telran.mishpahug.entities.StaticFields;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class StaticFieldsHandler {

    @GetMapping(URLConstants.staticFields)
    @CrossOrigin
    ResponseEntity getStaticFields (){
        return new ResponseEntity<>(new StaticFields(), HttpStatus.OK);
    }
}
