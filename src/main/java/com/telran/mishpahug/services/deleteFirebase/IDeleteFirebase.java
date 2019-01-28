package com.telran.mishpahug.services.deleteFirebase;

import com.telran.mishpahug.api.RequestsDTO.TokenDTO;
import org.springframework.http.ResponseEntity;

public interface IDeleteFirebase {

    ResponseEntity deleteToken(String token64, TokenDTO data);

}
