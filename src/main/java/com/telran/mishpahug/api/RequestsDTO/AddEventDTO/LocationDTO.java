package com.telran.mishpahug.api.RequestsDTO.AddEventDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
class LocationDTO implements Serializable {
     private double lat;
     private double lng;
}
