package com.telran.mishpahug.api.ResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter

public class MessageDTORes implements Serializable {
    private int code;
    private String message;
}
