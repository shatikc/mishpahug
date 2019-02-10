package com.telran.mishpahug.api.ResponseDTO.EventsListForCalendarDTORes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@NoArgsConstructor
@Getter

public class EventDTORes implements Serializable {
     private long eventId;
     private String title;
     private LocalDate date;
     private LocalTime time;
     private int duration;
     private String  status;

     public EventDTORes(long eventId, String title, LocalDate date, LocalTime time, int duration, String status) {
          this.eventId = eventId;
          this.title = title;
          this.date = date;
          this.time = time;
          this.duration = duration;
          this.status = status;
     }
}
