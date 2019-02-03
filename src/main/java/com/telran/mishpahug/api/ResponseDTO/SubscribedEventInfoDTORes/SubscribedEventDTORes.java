package com.telran.mishpahug.api.ResponseDTO.SubscribedEventInfoDTORes;

import com.telran.mishpahug.api.ResponseDTO.ListOfEventsInProgressDTOResponse.AddressDTORes;
import com.telran.mishpahug.api.ResponseDTO.ListOfEventsInProgressDTOResponse.OwnerDTORes;
import com.telran.mishpahug.api.ResponseDTO.ParticipationListDTORes.EventParentDTORes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

@NoArgsConstructor
@Getter

public class SubscribedEventDTORes extends EventParentDTORes {
    private LocalTime time;
    private int duration;
    private AddressDTORes address;
    private ArrayList<String> food;

    public SubscribedEventDTORes(long eventId, String title, String holiday, String confession, LocalDate date,
                                 String description, String status, OwnerDTORes owner, LocalTime time,
                                 int duration, AddressDTORes address, ArrayList<String> food) {
        super(eventId, title, holiday, confession, date, description, status, owner);
        this.time = time;
        this.duration = duration;
        this.address = address;
        this.food = food;
    }
}
