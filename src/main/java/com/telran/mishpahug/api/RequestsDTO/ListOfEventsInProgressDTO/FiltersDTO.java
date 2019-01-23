package com.telran.mishpahug.api.RequestsDTO.ListOfEventsInProgressDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter

public class FiltersDTO implements Serializable {

    private String dateFrom;
    private String dateTo;
    private String holidays;
    private String confession;
    private String food;

}
