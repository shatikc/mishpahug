package com.telran.mishpahug.api.ResponseDTO;

import com.telran.mishpahug.api.ResponseDTO.MyEventInfoDTORes.MyEventDTORes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;

@NoArgsConstructor
@AllArgsConstructor
@Getter

public class MyEventListDTORes implements Serializable {
    private ArrayList<MyEventDTORes> events;
}
