package com.telran.mishpahug.services.addFirebase;

import com.telran.mishpahug.api.RequestsDTO.TokenDTO;
import org.springframework.http.ResponseEntity;

public interface IAddFirebase {

    ResponseEntity addToken(String headers, TokenDTO token);
}
