package com.telran.mishpahug.services.subscribeToEvent;

import com.telran.mishpahug.entities.Event;
import com.telran.mishpahug.entities.Invited;
import com.telran.mishpahug.entities.Profile;
import com.telran.mishpahug.repository.subscribe.ISubscribeEventCRUD;
import com.telran.mishpahug.repository.subscribe.ISubscribeProfileCRUD;
import com.telran.mishpahug.services.parser.IParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class SubscribeToEventCRUDModel implements ISubscribeToEvent {

    @Autowired
    IParser parseSubscribe;

    @Autowired
    ISubscribeEventCRUD subscribeEventRepo;

    @Autowired
    ISubscribeProfileCRUD subscribeProfile;

    @Override
    @Transactional
    public ResponseEntity subscribeEvent(String token64, long eventId) {
         String [] emailPass = parseSubscribe.parseToken(token64);
         Profile profile = subscribeProfile.findProfile(emailPass[0],emailPass[1]);
         if(profile!=null){
             Event event = subscribeEventRepo.findEvent(eventId);
             if(event!=null){

             }
         }
        return new ResponseEntity<>("{{Error_401_sample}}", HttpStatus.UNAUTHORIZED);
    }


    private boolean isInvitedOnThisData(Profile profile,Event event){
        List<Long> setEventsId =  profile.getInvited().stream().filter(x->!x.isVoited()).
                mapToLong(Invited::getInvitedId).boxed().collect(Collectors.toList());
        for (Long id:setEventsId) {
            Event item = subscribeEventRepo.findEvent(id);
            if(item.getDate().isEqual(event.getDate())){return true;}
        }
        return false;
    }
}
