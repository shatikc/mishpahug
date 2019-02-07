package com.telran.mishpahug.services.unsubscribeFromEvent;

import com.telran.mishpahug.api.ResponseDTO.MessageDTORes;
import com.telran.mishpahug.entities.Event;
import com.telran.mishpahug.entities.Profile;
import com.telran.mishpahug.repository.unsubscribe.IUnsubscribeEventCRUD;
import com.telran.mishpahug.repository.unsubscribe.IUnsubscribeProfileCRUD;
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
public class UnsubscribeFromEventCRUDModel implements IUnsubscribeFromEvent {

    @Autowired
    IParser parseUnsubscribe;

    @Autowired
    IUnsubscribeEventCRUD eventUnsubscribeRepo;

    @Autowired
    IUnsubscribeProfileCRUD profileUnsubscribeRepo;

    @Override
    @Transactional
    public ResponseEntity unsubscribeFromEvent(String token64, long eventId) {
         String [] emailPass = parseUnsubscribe.parseToken(token64);
         Profile profile = profileUnsubscribeRepo.findProfile(emailPass[0],emailPass[1]);
         if(profile!=null){
             if(isSubscribedAndHasRightStatus(profile,eventId)){
                 Event event = eventUnsubscribeRepo.findEvent(eventId);
                 unsubscribe(profile,event);
                 profileUnsubscribeRepo.save(profile);
                 eventUnsubscribeRepo.save(event);
                 return new ResponseEntity<>(new MessageDTORes(200,"User unsubscribed" +
                         " from the event!"),HttpStatus.OK);
             }
             return new ResponseEntity<>(new MessageDTORes(409,"User can't unsubscribe" +
                     " from the event!"),HttpStatus.CONFLICT);
         }
        return new ResponseEntity<>("{{Error_401_sample}}", HttpStatus.UNAUTHORIZED);
    }


    private boolean isSubscribedAndHasRightStatus(Profile profile,long eventId){
        List<Event> events = profile.getEvents().stream().
                filter(x->x.getEventId()==eventId).
                collect(Collectors.toList());
        if(events.size()>0){
            Event item = events.get(0);
            return item.getStatus().equals("In progress");
            }
            return false;
        }

        private void unsubscribe(Profile profile,Event event){
            Set<Profile> subscribers =  event.getSubscribers();
            for (Profile item:subscribers) {
                if(item.equals(profile)){
                    subscribers.remove(item);
                    event.setSubscribers(subscribers);
                }
            }
            Set<Event> events = profile.getEvents();
            for (Event item:events) {
                if(item.equals(event)){
                    events.remove(item);
                    profile.setEvents(events);
                }
            }
        }

}
