package com.telran.mishpahug.services.myEventInfo;

import com.telran.mishpahug.api.ResponseDTO.MessageDTORes;
import com.telran.mishpahug.api.ResponseDTO.MyEventInfoDTORes.MyEventDTORes;
import com.telran.mishpahug.api.ResponseDTO.MyEventInfoDTORes.SubscriberInPendingDTORes;
import com.telran.mishpahug.api.ResponseDTO.MyEventInfoDTORes.SubscriberInProgressDTORes;
import com.telran.mishpahug.entities.*;
import com.telran.mishpahug.repository.myEvent.IMyEventFoodCRUD;
import com.telran.mishpahug.repository.myEvent.IMyEventInfoCRUD;
import com.telran.mishpahug.repository.myEvent.IMyEventProfileCRUD;
import com.telran.mishpahug.services.parser.IParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Repository
public class MyEventInfoCRUDModel implements IMyEventInfo {

    @Autowired
    IMyEventFoodCRUD eventAddFood;

    @Autowired
    IMyEventInfoCRUD eventInfoRepo;

    @Autowired
    IMyEventProfileCRUD eventCheckProf;

    @Autowired
    IParser parseEvent;

    @Override
    @Transactional
    public ResponseEntity getEventInfo(String token64, long eventId) {
        String[] emailPass = parseEvent.parseToken(token64);
        Profile profile = eventCheckProf.findProfile(emailPass[0], emailPass[1]);
        if (profile != null) {
            Event event = getEventIfExist(profile,eventId);
            if (event != null) {
                return new ResponseEntity<>(setMyEventDTORes(event), HttpStatus.OK);
            }
                return new ResponseEntity<>(new MessageDTORes(409,"User is not associated with the event!"),
                        HttpStatus.UNPROCESSABLE_ENTITY);
            }
            return new ResponseEntity<>("{{Error_401_sample}}", HttpStatus.UNAUTHORIZED);
        }

        private Event getEventIfExist(Profile profile,long eventId){
           List<Event> events = profile.getOwners().stream().filter(x->x.getEventId()==eventId).collect(Collectors.toList());
           if(events.size()>0){return events.get(0);}
           return null;
        }


        private MyEventDTORes setMyEventDTORes(Event event){
        ArrayList<String> foods = (ArrayList<String>) event.getFoodsOfEvent().stream().
                map(FoodEvent::getFood).collect(Collectors.toList());
        Set<Profile> subsc = event.getSubscribers();
        Set<Profile> invited = event.getInvited().stream().map(Invited::getUserId).collect(Collectors.toSet());
        subsc.addAll(invited);
        ArrayList<SubscriberInProgressDTORes> subscribers =
                (ArrayList<SubscriberInProgressDTORes>) getListSubscribersDTO(subsc,
                        event.getStatus(),event);
         return new MyEventDTORes(event.getEventId(),event.getTitle(),event.getHoliday(),
                    event.getConfession(),event.getDate(),event.getTime(),event.getDuration(),
                    foods,event.getDescription(),event.getStatus(),subscribers);
        }

        private List<SubscriberInProgressDTORes> getListSubscribersDTO(Set<Profile> profiles, String status,Event event){
          List<SubscriberInProgressDTORes> res = new ArrayList<>();
            for (Profile el:profiles) {
                res.add(getSubscriberFromProfile(el,status,event));
            }
          return res;
        }

        private SubscriberInProgressDTORes getSubscriberFromProfile(Profile profile, String status,Event event){
            ArrayList<String> pictures = parseEvent.getListOfPictures(profile);
            ArrayList<String> foods = parseEvent.getListOfFoods(profile);
            ArrayList<String> languages = parseEvent.getListOfLanguages(profile);
            List<Invited> list = profile.getInvited().stream().
                    filter(x->x.getEventId().getEventId()==(event.getEventId())).
                    collect(Collectors.toList());
            boolean isInvited = list.size()>0;
            if(status.equals("In progress")){
            return new SubscriberInProgressDTORes(profile.getUserId(),
                    profile.getFullName(), profile.getConfession(),profile.getGender(),profile.getAge(),
                    pictures,profile.getMaritalStatus(),foods,languages,profile.getRate(),
                    profile.getNumberOfVoters(), isInvited);
        }
        return new SubscriberInPendingDTORes(profile.getUserId(),
                    profile.getFullName(), profile.getConfession(),profile.getGender(),profile.getAge(),
                    pictures,profile.getMaritalStatus(),foods,languages,profile.getRate(),
                    profile.getNumberOfVoters(), isInvited,profile.getPhoneNumber());
        }

    }
