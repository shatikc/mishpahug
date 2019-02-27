package com.telran.mishpahug.api.RequestsDTO.ListOfEventsInProgressDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class ListOfEventsDTO implements Serializable {

  private LocationRadiusDTO location;
  private FiltersDTO filters;

}
