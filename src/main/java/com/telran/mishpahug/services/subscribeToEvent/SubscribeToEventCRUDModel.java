package com.telran.mishpahug.services.subscribeToEvent;

import com.telran.mishpahug.api.ResponseDTO.MessageDTORes;
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
import java.util.Set;
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
             if(event!=null&&!isInvitedOnThisData(profile,event)){
                 if(!profile.getEvents().contains(event)&&!event.getOwner_email().equals(profile)){
                     putNewDataInProfileAndEvent(profile,event);
                     subscribeProfile.save(profile);
                     subscribeEventRepo.save(event);
                     return new ResponseEntity<>(new MessageDTORes(200,"User subscribed" +
                             " to the event!"),HttpStatus.OK);
                 }
             }
             return new ResponseEntity<>(new MessageDTORes(409,"User is the owner of the" +
                     " event or already subscribed to it!"),HttpStatus.CONFLICT);
         }
        return new ResponseEntity<>("{{Error_401_sample}}", HttpStatus.UNAUTHORIZED);
    }


    private boolean isInvitedOnThisData(Profile profile,Event event){
        List<Event> setEvents = profile.getInvited().stream().
                filter(x->!x.isVoited()).
                map(Invited::getEventId).filter(x->x.getDate().isEqual(event.getDate())).
                collect(Collectors.toList());
        return setEvents.size()>0;
    }

    private void putNewDataInProfileAndEvent(Profile profile, Event event){
        Set<Event> events = profile.getEvents();
        events.add(event);
        profile.setEvents(events);
        Set<Profile> subscribers = event.getSubscribers();
        subscribers.add(profile);
        event.setSubscribers(subscribers);
    }
}
