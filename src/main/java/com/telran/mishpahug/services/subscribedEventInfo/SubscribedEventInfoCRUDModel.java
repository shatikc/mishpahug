package com.telran.mishpahug.services.subscribedEventInfo;

import com.telran.mishpahug.api.RequestsDTO.AddEventDTO.AddressDTO;
import com.telran.mishpahug.api.RequestsDTO.AddEventDTO.LocationDTO;
import com.telran.mishpahug.api.ResponseDTO.ListOfEventsInProgressDTOResponse.AddressDTORes;
import com.telran.mishpahug.api.ResponseDTO.ListOfEventsInProgressDTOResponse.OwnerDTORes;
import com.telran.mishpahug.api.ResponseDTO.MessageDTORes;
import com.telran.mishpahug.api.ResponseDTO.SubscribedEventInfoDTORes.OwnerPhoneDTORes;
import com.telran.mishpahug.api.ResponseDTO.SubscribedEventInfoDTORes.OwnerVotersDTORes;
import com.telran.mishpahug.api.ResponseDTO.SubscribedEventInfoDTORes.SubscribedEventDTORes;
import com.telran.mishpahug.entities.*;
import com.telran.mishpahug.repository.ISubscribedEventInfoCRUD;
import com.telran.mishpahug.services.parser.IParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class SubscribedEventInfoCRUDModel implements ISubscribedEventInfo {

    @Autowired
    IParser parseEventInfo;

    @Autowired
    ISubscribedEventInfoCRUD subscribedEventInfoRepo;

    @Override
    @Transactional
    public ResponseEntity getSubscribedEventInfo(String token64, long eventId) {
        String[] emailPass = parseEventInfo.parseToken(token64);
        Profile profile = subscribedEventInfoRepo.findProfile(emailPass[0], emailPass[1]);
        if(profile!=null){
           List<Event> list = getEventByEventIdFromSubscribed(profile,eventId);
           if(list.size()>0){
              Event event = list.get(0);
               SubscribedEventDTORes response = getResponse(profile,event);
               return new ResponseEntity<>(response,HttpStatus.OK);
           }
           return new ResponseEntity<>(new MessageDTORes(409,"User is not associated with the event!"),
                   HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("{{Error_401_sample}}", HttpStatus.UNAUTHORIZED);
    }

    private List<Event> getEventByEventIdFromSubscribed(Profile profile, long eventId){
        return profile.getEvents().stream().
                filter(x->x.getEventId()==eventId).
                collect(Collectors.toList());
    }

    private SubscribedEventDTORes getResponse(Profile prof,Event event){
        ArrayList<String> foods = (ArrayList<String>) event.getFoodsOfEvent().stream().
                map(FoodEvent::getFood).
                collect(Collectors.toList());
        return new SubscribedEventDTORes(event.getEventId(),event.getTitle(),event.getHoliday(),event.getConfession(),
                event.getDate(),event.getDescription(),event.getStatus(),getOwner(event),event.getTime(),
                event.getDuration(),getAddress(event),foods);
    }

    private AddressDTORes getAddress(Event event){
        if(event.getStatus().equals("Pending")){
            LocationDTO location = new LocationDTO(event.getLat(),event.getLng());
            return new AddressDTO(event.getCity(),event.getPlace_id(),location);
        }
        return new AddressDTORes(event.getCity());
    }

    private OwnerDTORes getOwner(Event event){
        Profile prof = event.getOwner_email();
        ArrayList<String> pictures = (ArrayList<String>) prof.getPictures().stream().
                sorted((a, b)->a.compare(a,b)).
                map(Picture::getUrl).
                collect(Collectors.toList());
        ArrayList<String>foods = parseEventInfo.getListOfFoods(prof);
        ArrayList<String>lang = parseEventInfo.getListOfLanguages(prof);
        if(event.getStatus().equals("Pending")){
            return new OwnerPhoneDTORes(prof.getFullName(),prof.getConfession(),prof.getGender(),prof.getAge(),
                   pictures, prof.getMaritalStatus(),foods,lang,
                    prof.getRate(),prof.getNumberOfVoters(),prof.getPhoneNumber());
        }
        return new OwnerVotersDTORes(prof.getFullName(),prof.getConfession(),prof.getGender(),prof.getAge(),
                   pictures, prof.getMaritalStatus(),foods,lang,
                    prof.getRate(),prof.getNumberOfVoters());
    }


}
