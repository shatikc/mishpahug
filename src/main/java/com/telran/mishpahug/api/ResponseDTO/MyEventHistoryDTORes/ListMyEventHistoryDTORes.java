package com.telran.mishpahug.api.ResponseDTO.MyEventHistoryDTORes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;

@NoArgsConstructor
@AllArgsConstructor
@Getter

public class ListMyEventHistoryDTORes implements Serializable {
    private ArrayList<MyEventHistoryDTORes> events;
}
