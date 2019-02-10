package com.telran.mishpahug.api.RequestsDTO.AddEventDTO;

import com.telran.mishpahug.api.ResponseDTO.ListOfEventsInProgressDTOResponse.AddressDTORes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter

public class AddressDTO extends AddressDTORes  implements Serializable {
    private String place_id;
    private LocationDTO location;

    public AddressDTO(String city, String place_id, LocationDTO location) {
        super(city);
        this.place_id = place_id;
        this.location = location;
    }
}
