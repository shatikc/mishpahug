package com.telran.mishpahug.api.ResponseDTO.MyEventInfoDTORes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@NoArgsConstructor
@Getter
@Setter

public class SubscriberInPendingDTORes extends SubscriberInProgressDTORes {
    private String phoneNumber;

    public SubscriberInPendingDTORes(long userId, String fullName, String confession, String gender, int age,
                                     ArrayList<String> pictureLink, String maritalStatus,
                                     ArrayList<String> foodPreferences, ArrayList<String> languages, double rate,
                                     int numberOfVoters, boolean isInvited, String phoneNumber) {
        super(userId, fullName, confession, gender, age, pictureLink, maritalStatus, foodPreferences, languages,
                rate, numberOfVoters, isInvited);
        this.phoneNumber = phoneNumber;
    }
}
