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
          Event event = isEventExist(profile,eventId);
              if(event!=null){
                  Profile profByUserId = isSubscribedOnThisEvent(event,userId);
                  if(profByUserId!=null){
                      if(!isInvited(profByUserId,event)){
                         removeSubscriptionIfExist(profByUserId,event);
                         removeSubscribersIfExist(event,profByUserId);
                         Invited invitedProfile = new Invited(profByUserId,event,false);
                         setInvited(event,profByUserId,invitedProfile);
                         inviteEventRepo.save(event);
                         inviteProfileRepo.save(profByUserId);
                         inviteInvitedRepo.save(invitedProfile);
                         return new ResponseEntity<>(new InviteToEventDTORes(userId, true), HttpStatus.OK);
                      }
                  }
              }
              return new ResponseEntity<>(new MessageDTORes(409,"User is already invited to" +
                    " the event or is not subscribed to the event!"),HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("{{Error_401_sample}}", HttpStatus.UNAUTHORIZED);
    }

    private void setInvited(Event event, Profile profile, Invited invited){
        Set<Invited> invitedEvent = event.getInvited();
        invitedEvent.add(invited);
        event.setInvited(invitedEvent);
        Set<Invited> invitedProfile = profile.getInvited();
        invitedProfile.add(invited);
        profile.setInvited(invitedProfile);
    }

    private void removeSubscribersIfExist(Event event, Profile profile){
        Set<Profile> subscribers = event.getSubscribers().stream().filter(x->!x.equals(profile)).collect(Collectors.toSet());
        event.setSubscribers(subscribers);
    }

    private void removeSubscriptionIfExist(Profile profile,Event event){
        Set<Event> subscriptions = profile.getEvents().stream().filter(x->!x.getDate().isEqual(event.getDate())).collect(Collectors.toSet());
        profile.setEvents(subscriptions);
    }

    private boolean isInvited(Profile profile, Event event){
        List<Invited> list = profile.getInvited().stream().filter(x->x.getEventId().equals(event)).collect(Collectors.toList());
        List<Invited> sameData = profile.getInvited().stream().
                filter(x->x.getEventId().getDate().isEqual(event.getDate())).collect(Collectors.toList());
        if(list.size()!=0){
            return sameData.size()!=0;
        }
        return false;
    }

    private Profile isSubscribedOnThisEvent(Event event, long userId){
        List<Profile> subscriber = event.getSubscribers().stream().filter(x->x.getUserId()==userId).collect(Collectors.toList());
        if(subscriber.size()!=0){return subscriber.get(0);}
        return null;
    }

    private Event isEventExist(Profile profile,long eventId){
        List<Event>owner = profile.getOwners().stream().filter(x->x.getEventId()==eventId).collect(Collectors.toList());
        if(owner.size()!=0){return owner.get(0);}
        return null;
    }
}
