package com.telran.mishpahug.api.ResponseDTO.NotificationsListDTORes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;

@NoArgsConstructor
@AllArgsConstructor
@Getter

public class ListNotifications implements Serializable {
    private ArrayList<Notification> notifications;
}
