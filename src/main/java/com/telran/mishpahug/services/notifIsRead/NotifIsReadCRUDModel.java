package com.telran.mishpahug.services.notifIsRead;

import com.telran.mishpahug.api.ResponseDTO.MessageDTORes;
import com.telran.mishpahug.entities.Notification;
import com.telran.mishpahug.entities.Profile;
import com.telran.mishpahug.repository.notifIsRead.INotifIsReadProfileCRUD;
import com.telran.mishpahug.repository.notifIsRead.INotificationIsRead;
import com.telran.mishpahug.services.parser.IParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.Set;

@Repository
public class NotifIsReadCRUDModel implements INotifIsRead {

    @Autowired
    INotificationIsRead notificationRepo;

    @Autowired
    IParser parseNotifRead;

    @Autowired
    INotifIsReadProfileCRUD notifIsReadRepo;

    @Override
    @Transactional
    public ResponseEntity putNotificationIsRead(String token64, long notId) {
        String[] emailPass = parseNotifRead.parseToken(token64);
        Profile profile = notifIsReadRepo.findProfile(emailPass[0], emailPass[1]);
        if(profile!=null){
            Notification notification = notificationRepo.getEntitie(notId);
            if(notification!=null){
                notification.setRead(true);
                notificationRepo.save(notification);
                Set<Notification> notifications = profile.getNotifications();
                Set<Notification> afterChanging = getActualNotifications(notifications,notId);
                profile.setNotifications(afterChanging);
                notifIsReadRepo.save(profile);
                return new ResponseEntity<>(new MessageDTORes(200,"Notification is updated!"), HttpStatus.OK);
            }
            return new ResponseEntity<>(new MessageDTORes(409,"User can't read this notification!"),HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("{{Error_401_sample}}",HttpStatus.UNAUTHORIZED);
    }

    private Set<Notification> getActualNotifications(Set<Notification> set, long id){
        for(Iterator<Notification> it = set.iterator();it.hasNext();){
            Notification item = it.next();
            if(item.getNotificationId()==id){
                item.setRead(true);
                return set;
            }
        }
        return set;
    }
}
