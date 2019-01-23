package com.telran.mishpahug.model;
import org.springframework.http.ResponseEntity;


public interface IHugAddUser {

    ResponseEntity addNewUser(String token);

}
