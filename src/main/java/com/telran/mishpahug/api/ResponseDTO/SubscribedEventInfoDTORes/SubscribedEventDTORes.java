package com.telran.mishpahug.api.ResponseDTO.SubscribedEventInfoDTORes;

import com.telran.mishpahug.api.ResponseDTO.ListOfEventsInProgressDTOResponse.AddressDTORes;
import com.telran.mishpahug.api.ResponseDTO.ParticipationListDTORes.EventParentDTORes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@NoArgsConstructor
@AllArgsConstructor
@Getter

public class SubscribedEventDTORes extends EventParentDTORes {
    private String time;
    private int duration;
    private AddressDTORes address;
    private ArrayList<String> food;
}
