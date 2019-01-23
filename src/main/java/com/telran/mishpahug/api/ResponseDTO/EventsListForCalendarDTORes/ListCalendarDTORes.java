package com.telran.mishpahug.api.ResponseDTO.EventsListForCalendarDTORes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;

@NoArgsConstructor
@AllArgsConstructor
@Getter

public class ListCalendarDTORes implements Serializable {
    private ArrayList<EventDTORes> myEvents;
    private ArrayList<EventDTORes> subscribedEvents;
}
