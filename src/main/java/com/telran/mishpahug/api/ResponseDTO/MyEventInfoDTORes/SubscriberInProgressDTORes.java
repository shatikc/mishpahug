package com.telran.mishpahug.api.ResponseDTO.MyEventInfoDTORes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class SubscriberInProgressDTORes implements Serializable {

    private long userId;
    private String fullName;
    private String confession;
    private String gender;
    private int age;
    private ArrayList<String> pictureLink;
    private String maritalStatus;
    private ArrayList<String> foodPreferences;
    private ArrayList<String> languages;
    private double rate;
    private int numberOfVoters;
    private boolean isInvited;
}
