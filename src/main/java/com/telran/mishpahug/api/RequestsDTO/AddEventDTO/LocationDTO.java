package com.telran.mishpahug.api.RequestsDTO.AddEventDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class LocationDTO implements Serializable {
     private double lat;
     private double lng;
}
