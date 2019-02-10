package com.telran.mishpahug.api.ResponseDTO.MyEventHistoryDTORes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

@NoArgsConstructor
@AllArgsConstructor
@Getter

public class MyEventHistoryDTORes implements Serializable {
    private long eventId;
    private String title;
    private String holiday;
    private String confession;
    private LocalDate date;
    private ArrayList<String> food;
    private String description;
    private String status;

}
