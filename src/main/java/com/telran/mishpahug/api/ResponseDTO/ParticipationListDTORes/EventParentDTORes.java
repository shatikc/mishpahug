package com.telran.mishpahug.api.ResponseDTO.ParticipationListDTORes;

import com.telran.mishpahug.api.ResponseDTO.ListOfEventsInProgressDTOResponse.OwnerDTORes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter

public class EventParentDTORes implements Serializable {
    private long eventId;
    private String title;
    private String holiday;
    private String confession;
    private LocalDate date;
    private String description;
    private String status;
    private OwnerDTORes owner;
}
