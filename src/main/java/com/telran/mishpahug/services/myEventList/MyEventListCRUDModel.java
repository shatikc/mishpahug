package com.telran.mishpahug.services.myEventList;

import com.telran.mishpahug.api.ResponseDTO.MyEventInfoDTORes.MyEventDTORes;
import com.telran.mishpahug.api.ResponseDTO.MyEventInfoDTORes.SubscriberInPendingDTORes;
import com.telran.mishpahug.api.ResponseDTO.MyEventInfoDTORes.SubscriberInProgressDTORes;
import com.telran.mishpahug.api.ResponseDTO.MyEventListDTORes;
import com.telran.mishpahug.entities.*;
import com.telran.mishpahug.repository.IMyEventListCRUD;
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
public class MyEventListCRUDModel implements IMyEventList {

    @Autowired
    IParser parseMyList;

    @Autowired
    IMyEventListCRUD listRepo;

    @Override
    @Transactional
    public ResponseEntity getMyEventList(String token64) {
         String[] emailPass = parseMyList.parseToken(token64);
        Profile profile = listRepo.findProfile(emailPass[0], emailPass[1]);
        if(profile!=null){
            List<Event> events = getSortedMyEventList(profile);
           return new ResponseEntity<>(new MyEventListDTORes(getListDTO(events)),HttpStatus.OK);
        }
        return new ResponseEntity<>("{{Error_401_sample}}", HttpStatus.UNAUTHORIZED);
    }

    private List<Event> getSortedMyEventList(Profile profile){
        return profile.getOwners().stream().
                filter(x->!x.getStatus().equals("Done")).
                sorted((a,b)->a.compare(a,b)).
                collect(Collectors.toList());
    }

    private ArrayList<MyEventDTORes> getListDTO(List<Event>events){
        ArrayList<MyEventDTORes> res = new ArrayList<>();
        for (Event event: events) {
            res.add(getMyEventDTORes(event));
        }
        return res;
    }

    private MyEventDTORes getMyEventDTORes(Event event){
        ArrayList<String> food = (ArrayList<String>) event.getFoodsOfEvent().stream().
                map(FoodEvent::getFood).collect(Collectors.toList());
        return new MyEventDTORes(event.getEventId(),event.getTitle(),event.getHoliday(),event.getConfession(),
                event.getDate(),event.getTime(),event.getDuration(),food,event.getDescription(),
                event.getStatus(),getSubscriberDTO(event));
    }

    private ArrayList<SubscriberInProgressDTORes> getSubscriberDTO(Event event){
        ArrayList<SubscriberInProgressDTORes> res = new ArrayList<>();
        Set<Profile>subscribers = event.getSubscribers();
        for (Profile prof:subscribers) {
            SubscriberInProgressDTORes item;
            ArrayList<String> pictures = (ArrayList<String>) prof.getPictures().stream().
                sorted((a, b) -> a.compare(a, b)).map(Picture::getUrl).collect(Collectors.toList());
            if(event.getStatus().equals("In progress")) {
                item = new SubscriberInProgressDTORes(prof.getUserId(), prof.getFullName(),
                        prof.getConfession(), prof.getGender(), prof.getAge(), pictures, prof.getMaritalStatus(),
                        parseMyList.getListOfFoods(prof), parseMyList.getListOfLanguages(prof), prof.getRate(),
                        prof.getNumberOfVoters(), isInvitedOnEvent(prof, event));
            }else{
                item = new SubscriberInPendingDTORes(prof.getUserId(), prof.getFullName(),
                        prof.getConfession(), prof.getGender(), prof.getAge(), pictures, prof.getMaritalStatus(),
                        parseMyList.getListOfFoods(prof), parseMyList.getListOfLanguages(prof), prof.getRate(),
                        prof.getNumberOfVoters(), isInvitedOnEvent(prof, event),prof.getPhoneNumber());
            }
            res.add(item);
        }
        return res;
    }

    private boolean isInvitedOnEvent (Profile profile, Event event){
        Set<Invited> set = event.getInvited().stream().
                filter(x->x.getUserId().getUserId()==profile.getUserId()).collect(Collectors.toSet());
        return set.size()>0;
    }
}
