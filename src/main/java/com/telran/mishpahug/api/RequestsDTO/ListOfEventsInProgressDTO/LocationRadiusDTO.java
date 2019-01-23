package com.telran.mishpahug.api.RequestsDTO.ListOfEventsInProgressDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter

public class LocationRadiusDTO implements Serializable {

    private double lat;
    private double lng;
    private double radius;

}
