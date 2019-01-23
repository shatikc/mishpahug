package com.telran.mishpahug.api.ResponseDTO.SubscribedEventInfoDTORes;

import com.telran.mishpahug.api.ResponseDTO.ListOfEventsInProgressDTOResponse.OwnerDTORes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter

public class OwnerVotersDTORes extends OwnerDTORes implements Serializable {
    private int numberOfVoters;
}
