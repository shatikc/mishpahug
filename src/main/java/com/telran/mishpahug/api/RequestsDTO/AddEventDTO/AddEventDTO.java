package com.telran.mishpahug.api.RequestsDTO.AddEventDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;

@NoArgsConstructor
@AllArgsConstructor
@Getter


public class AddEventDTO implements Serializable {

    private String title;
    private String holiday;
    private AddressDTO address;
    private String confession;
    private String date;
    private String time;
    private int duration;
    private ArrayList<String> food;
    private String description;

}
