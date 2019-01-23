package com.telran.mishpahug.api.RequestsDTO.AddEventDTO;

import com.telran.mishpahug.api.ResponseDTO.ListOfEventsInProgressDTOResponse.AddressDTORes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter

public class AddressDTO extends AddressDTORes  implements Serializable {
    private String place_id;
    private LocationDTO location;
}
