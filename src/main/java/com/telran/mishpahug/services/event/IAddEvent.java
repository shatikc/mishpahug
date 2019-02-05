package com.telran.mishpahug.services.event;

import com.telran.mishpahug.api.RequestsDTO.AddEventDTO.AddEventDTO;
import org.springframework.http.ResponseEntity;

public interface IAddEvent {

    ResponseEntity createNewEvent(String token64, AddEventDTO event);

}
