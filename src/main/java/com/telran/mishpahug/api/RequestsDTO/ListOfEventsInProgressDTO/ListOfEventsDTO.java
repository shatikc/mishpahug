package com.telran.mishpahug.api.RequestsDTO.ListOfEventsInProgressDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter

public class ListOfEventsDTO implements Serializable {

  private LocationRadiusDTO location;
  private FiltersDTO filters;

}
