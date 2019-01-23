package com.telran.mishpahug.api.ResponseDTO.ListOfEventsInProgressDTOResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;

@NoArgsConstructor
@AllArgsConstructor
@Getter

public class ListOfEventsDTORes implements Serializable {
    private ArrayList<ContentDTORes> content;
    private long totalElements;
    private int totalPages;
    private int size;
    private int number;
    private int numberOfElements;
    private boolean first;
    private boolean last;
    // unknown type of value
    private Object sort;

}
