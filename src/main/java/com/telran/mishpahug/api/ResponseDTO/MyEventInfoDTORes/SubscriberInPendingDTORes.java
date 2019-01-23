package com.telran.mishpahug.api.ResponseDTO.MyEventInfoDTORes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter

public class SubscriberInPendingDTORes extends SubscriberInProgressDTORes {
    private String phoneNumber;
}
