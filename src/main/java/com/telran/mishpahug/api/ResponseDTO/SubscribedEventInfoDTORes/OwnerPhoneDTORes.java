package com.telran.mishpahug.api.ResponseDTO.SubscribedEventInfoDTORes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter

public class OwnerPhoneDTORes extends OwnerVotersDTORes {
    private String phoneNumber;
}
