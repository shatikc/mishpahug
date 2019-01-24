package com.telran.mishpahug.services.login;

import org.springframework.http.ResponseEntity;

public interface ILogin {

    ResponseEntity loginUser (String headers);
}
