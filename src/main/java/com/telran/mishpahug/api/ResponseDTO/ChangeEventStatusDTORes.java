package com.telran.mishpahug.api.ResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter

public class ChangeEventStatusDTORes implements Serializable {
    private long eventId;
    private String status;
}
