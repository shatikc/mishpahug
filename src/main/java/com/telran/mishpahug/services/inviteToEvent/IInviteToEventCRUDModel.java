package com.telran.mishpahug.services.inviteToEvent;

import com.telran.mishpahug.api.ResponseDTO.InviteToEventDTORes;
import com.telran.mishpahug.api.ResponseDTO.MessageDTORes;
import com.telran.mishpahug.entities.Event;
import com.telran.mishpahug.entities.Invited;
import com.telran.mishpahug.entities.Profile;
import com.telran.mishpahug.repository.invite.IInviteInvitedCRUD;
import com.telran.mishpahug.repository.invite.IInviteProfileCRUD;
import com.telran.mishpahug.repository.invite.IInviteToEventCRUD;
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
public class IInviteToEventCRUDModel implements IInviteToEvent {

    @Autowired
    IParser parseInvite;

    @Autowired
    IInviteToEventCRUD inviteEventRepo;

    @Autowired
    IInviteProfileCRUD inviteProfileRepo;

    @Autowired
    IInviteInvitedCRUD inviteInvitedRepo;

    @Override
    @Transactional
    public ResponseEntity inviteToEvent(String token64, long eventId, long userId) {
        String [] emailPass = parseInvite.parseToken(token64);
        Profile profile = inviteProfileRepo.findProfile(emailPass[0],emailPass[1]);
        if(profile!=null){
          Event event = inviteEventRepo.findEvent(eventId);
          Profile profUser = inviteProfileRepo.findProfileByUserId(userId);
          if(isSubscribed(event,profUser)){
              if(!alreadyIsInvited(event,profUser)) {
                  cancelAllOtherSubscriptionsOnThisDate(event, profUser);
                  Invited invited = new Invited(profUser,event,false);
                  inviteProfileRepo.save(profUser);
                  inviteEventRepo.save(event);
                  inviteInvitedRepo.save(invited);
                  return new ResponseEntity<>(new InviteToEventDTORes(userId, true), HttpStatus.OK);
              }
          }
          return new ResponseEntity<>(new MessageDTORes(409,"User is already invited to" +
                  " the event or is not subscribed to the event!"),HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("{{Error_401_sample}}", HttpStatus.UNAUTHORIZED);
    }

    private boolean isSubscribed(Event event, Profile profile){
            return  event.getSubscribers().contains(profile);
    }

    private boolean alreadyIsInvited(Event event, Profile profile){
        List<Event> eventInvited = profile.getInvited().stream().
                map(Invited::getEventId).
                filter(x->x.getDate().isEqual(event.getDate())).collect(Collectors.toList());
        return eventInvited.size()>0;
    }

    private void cancelAllOtherSubscriptionsOnThisDate(Event event,Profile profile){
        Set<Event> newEvents = profile.getEvents().stream().
                filter(x->!x.getDate().isEqual(event.getDate())).collect(Collectors.toSet());
        Set<Profile> newSubscribers = event.getSubscribers().stream().
                filter(x->x.getUserId()!=profile.getUserId()).collect(Collectors.toSet());
        profile.setEvents(newEvents);
        event.setSubscribers(newSubscribers);
    }
}
