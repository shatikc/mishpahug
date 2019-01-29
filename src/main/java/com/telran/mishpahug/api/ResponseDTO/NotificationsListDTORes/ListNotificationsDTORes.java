package com.telran.mishpahug.api.ResponseDTO.NotificationsListDTORes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;

@NoArgsConstructor
@AllArgsConstructor
@Getter

public class ListNotificationsDTORes implements Serializable {
    private ArrayList<NotificationDTORes> notifications;
}
