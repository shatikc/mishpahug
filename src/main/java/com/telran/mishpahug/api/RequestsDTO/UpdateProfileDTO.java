package com.telran.mishpahug.api.RequestsDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;

@NoArgsConstructor
@AllArgsConstructor
@Getter

public class UpdateProfileDTO implements Serializable {

    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String gender;
    private String  maritalStatus;
    private String confession;
    private ArrayList<String> pictureLink;
    private String  phoneNumber;
    private ArrayList<String> foodPreferences;
    private ArrayList<String> languages;
    private String description;

}
