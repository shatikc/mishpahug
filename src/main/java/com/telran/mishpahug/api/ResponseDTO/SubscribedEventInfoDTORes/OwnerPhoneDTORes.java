package com.telran.mishpahug.api.ResponseDTO.SubscribedEventInfoDTORes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;


@AllArgsConstructor
@Getter

public class OwnerPhoneDTORes extends OwnerVotersDTORes {
    private String phoneNumber;

    public OwnerPhoneDTORes(String fullName, String confession, String gender, int age, ArrayList<String> pictureLink,
                            String maritalStatus, ArrayList<String> foodPreferences, ArrayList<String> languages,
                            double rate, int numberOfVoters, String phoneNumber) {
        super(fullName, confession, gender, age, pictureLink, maritalStatus, foodPreferences, languages, rate, numberOfVoters);
        this.phoneNumber = phoneNumber;
    }
}
