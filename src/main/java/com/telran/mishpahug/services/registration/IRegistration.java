package com.telran.mishpahug.services.registration;
import org.springframework.http.ResponseEntity;


public interface IRegistration {

    ResponseEntity addNewUser(String headers);

}
