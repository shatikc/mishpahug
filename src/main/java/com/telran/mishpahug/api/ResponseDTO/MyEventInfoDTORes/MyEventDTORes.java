package com.telran.mishpahug.api.ResponseDTO.MyEventInfoDTORes;

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

public class MyEventDTORes implements Serializable {

    private long eventId;
    private String title;
    private String holiday;
    private String confession;
    private LocalDate date;
    private LocalTime time;
    private int duration;
    private ArrayList<String> food;
    private String description;
    private String status;
    private ArrayList<SubscriberInProgressDTORes> participants;
}
