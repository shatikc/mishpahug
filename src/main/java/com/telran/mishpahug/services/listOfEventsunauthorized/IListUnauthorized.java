package com.telran.mishpahug.services.listOfEventsunauthorized;

import com.telran.mishpahug.api.RequestsDTO.ListOfEventsInProgressDTO.ListOfEventsDTO;
import org.springframework.http.ResponseEntity;

public interface IListUnauthorized {

    ResponseEntity getListOfEventsByRadius(int page, int size, ListOfEventsDTO data);

}
