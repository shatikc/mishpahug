package com.telran.mishpahug.api.ResponseDTO.ListOfEventsInProgressDTOResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

@NoArgsConstructor
@AllArgsConstructor
@Getter

public class ContentDTORes implements Serializable {
    private Long eventId;
    private String title;
    private String holiday;
    private String confession;
    private LocalDate date;
    private LocalTime time;
    private int duration;
    private AddressDTORes address;
    private ArrayList<String> food;
    private String description;
    private OwnerDTORes owner;

}
