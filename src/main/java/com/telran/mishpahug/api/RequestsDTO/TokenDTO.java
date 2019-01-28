package com.telran.mishpahug.api.RequestsDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter

public class TokenDTO implements Serializable {
    private  String firebaseToken;
}


