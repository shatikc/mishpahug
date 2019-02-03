package com.telran.mishpahug.api.ResponseDTO.ParticipationListDTORes;

import com.telran.mishpahug.api.ResponseDTO.SubscribedEventInfoDTORes.SubscribedEventDTORes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;

@NoArgsConstructor
@AllArgsConstructor
@Getter

public class SubscribersListDTORes implements Serializable {
    private ArrayList<EventParentDTORes> events;
}
