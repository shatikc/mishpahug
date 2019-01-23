package com.telran.mishpahug.api.ResponseDTO.ListOfEventsInProgressDTOResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;

@NoArgsConstructor
@AllArgsConstructor
@Getter

public class OwnerDTORes implements Serializable {
    private String fullName;
    private String confession;
    private String gender;
    private int age;
    private ArrayList<String> pictureLink;
    private String maritalStatus;
    private ArrayList<String> foodPreferences;
    private ArrayList<String> languages;
    private double rate;
}
