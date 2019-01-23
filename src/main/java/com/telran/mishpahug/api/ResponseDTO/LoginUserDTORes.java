package com.telran.mishpahug.api.ResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter

public class LoginUserDTORes implements Serializable {
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String gender;
    private String maritalStatus;
    private String confession;
    private ArrayList<String> pictureLink;
    private String phoneNumber;
    private ArrayList<String> foodPreferences;
    private ArrayList<String> languages;
    private String description;
    private double  rate;
    private int numberOfVoters;
}
