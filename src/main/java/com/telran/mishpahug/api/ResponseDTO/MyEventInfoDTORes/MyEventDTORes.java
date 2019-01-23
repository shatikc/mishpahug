package com.telran.mishpahug.api.ResponseDTO.MyEventInfoDTORes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;

@NoArgsConstructor
@AllArgsConstructor
@Getter

public class MyEventDTORes implements Serializable {

    private long eventId;
    private String title;
    private String holiday;
    private String confession;
    private String date;
    private String time;
    private int duration;
    private ArrayList<String> food;
    private String description;
    private String status;
    private ArrayList<SubscriberInProgressDTORes> participants;
}
