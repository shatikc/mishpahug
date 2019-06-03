package com.telran.mishpahug.services.listNotifications;

import com.telran.mishpahug.api.ResponseDTO.NotificationsListDTORes.ListNotificationsDTORes;
import com.telran.mishpahug.api.ResponseDTO.NotificationsListDTORes.NotificationDTORes;
import com.telran.mishpahug.entities.Notification;
import com.telran.mishpahug.entities.Profile;
import com.telran.mishpahug.repository.IListNotificationCRUD;
import com.telran.mishpahug.services.parser.IParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Repository
public class ListNotificationsCRUDModel implements IListNotifications {

    @Autowired
    IListNotificationCRUD listRepo;

    @Autowired
    IParser parseList;



    @Override
    @Transactional
    public ResponseEntity getList(String token64) {
        String [] emailPass = parseList.parseToken(token64);
        Profile prof = listRepo.findProfile(emailPass[0],emailPass[1]);
        if(prof!=null){
            ArrayList<NotificationDTORes> list = (ArrayList<NotificationDTORes>) prof.getNotifications().stream().
                    sorted((a, b)->a.compare(a,b)).map(Notification::transformToDTORes).
                    collect(Collectors.toList());
            return new ResponseEntity<>(new ListNotificationsDTORes(list), HttpStatus.OK);
        }
        return new ResponseEntity<>("{{Error_401_sample}}", HttpStatus.UNAUTHORIZED);
    }
}
