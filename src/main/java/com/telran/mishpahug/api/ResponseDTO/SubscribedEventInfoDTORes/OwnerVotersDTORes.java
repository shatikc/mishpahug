package com.telran.mishpahug.api.ResponseDTO.SubscribedEventInfoDTORes;

import com.telran.mishpahug.api.ResponseDTO.ListOfEventsInProgressDTOResponse.OwnerDTORes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;

@NoArgsConstructor
@Getter

public class OwnerVotersDTORes extends OwnerDTORes implements Serializable {
    private int numberOfVoters;

    public OwnerVotersDTORes(String fullName, String confession, String gender, int age, ArrayList<String> pictureLink,
                             String maritalStatus, ArrayList<String> foodPreferences, ArrayList<String> languages,
                             double rate, int numberOfVoters) {
        super(fullName, confession, gender, age, pictureLink, maritalStatus, foodPreferences, languages, rate);
        this.numberOfVoters = numberOfVoters;
    }

}
