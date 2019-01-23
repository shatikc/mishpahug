package com.telran.mishpahug.api.ResponseDTO.EventsListForCalendarDTORes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter

public class EventDTORes implements Serializable {
     private long eventId;
     private String title;
     private String date;
     private String time;
     private String duration;
     private String  status;
}
