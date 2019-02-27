package com.telran.mishpahug.controllers;

import com.telran.mishpahug.api.RequestsDTO.ListOfEventsInProgressDTO.ListOfEventsDTO;
import com.telran.mishpahug.api.URLConstants;
import com.telran.mishpahug.services.listOfEventsunauthorized.IListUnauthorized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ListOfEventsUnauthorizedHandler {

    @Autowired
    IListUnauthorized getListService;

    @PostMapping(URLConstants.eventListInProgress)
    @CrossOrigin
    public ResponseEntity getListOfEvents
            (@RequestParam("page")int page, @RequestParam("size") int size, @RequestBody ListOfEventsDTO data){
        return getListService.getListOfEventsByRadius(page,size,data);
    }



}
