package com.telran.mishpahug.services.participationList;

import com.telran.mishpahug.api.ResponseDTO.ListOfEventsInProgressDTOResponse.AddressDTORes;
import com.telran.mishpahug.api.ResponseDTO.ListOfEventsInProgressDTOResponse.OwnerDTORes;
import com.telran.mishpahug.api.ResponseDTO.ParticipationListDTORes.EventParentDTORes;
import com.telran.mishpahug.api.ResponseDTO.ParticipationListDTORes.SubscribersListDTORes;
import com.telran.mishpahug.api.ResponseDTO.SubscribedEventInfoDTORes.OwnerPhoneDTORes;
import com.telran.mishpahug.api.ResponseDTO.SubscribedEventInfoDTORes.OwnerVotersDTORes;
import com.telran.mishpahug.api.ResponseDTO.SubscribedEventInfoDTORes.SubscribedEventDTORes;
import com.telran.mishpahug.entities.*;
import com.telran.mishpahug.repository.IParticipationListCRUD;
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
public class PartListCRUDModel implements IPartList {

    @Autowired
    IParser parsePartList;

    @Autowired
    IParticipationListCRUD partListRepo;

    @Override
    @Transactional
    public ResponseEntity getListOfParticipations(String token64) {
        String[] emailPass = parsePartList.parseToken(token64);
        Profile profile = partListRepo.findProfile(emailPass[0],emailPass[1]);
        if(profile!=null){
              ArrayList<Event> filtered = filterEvents(profile);
              return new ResponseEntity<>(getListEventDTORes(filtered,profile),HttpStatus.OK);
        }
        return new ResponseEntity<>("{{Error_401_sample}}", HttpStatus.UNAUTHORIZED);
    }


    private ArrayList<Event> filterEvents(Profile profile){
        ArrayList<Event> events = new ArrayList<>();
        events.addAll(profile.getEvents());
        events.sort((a,b)->a.compare(a,b));
        List<Long> invited = profile.getInvited().stream().
                filter(Invited::isVoited).map(Invited::getInvitedId).collect(Collectors.toList());
        for (Event el:events) {
            if(el.getStatus().equals("Done")){
                if(invited.contains(el.getEventId())){
                    events.remove(el);
                }
            }
        }
        return events;
    }

    private SubscribersListDTORes getListEventDTORes(ArrayList<Event> events, Profile profile){
        ArrayList<EventParentDTORes> list = new ArrayList<>();
        for (Event element:events) {
            EventParentDTORes item = getEventParentDTO(element,profile);
            list.add(item);
        }
        return new SubscribersListDTORes(list);
    }


    private EventParentDTORes getEventParentDTO(Event event, Profile profile){
        if(event.getStatus().equals("Done")){
            return new EventParentDTORes(event.getEventId(),event.getTitle(),event.getHoliday(),event.getConfession(),
                    event.getDate(),event.getDescription(),event.getStatus(),getOwnerDTO(profile,event.getStatus()));
        }
        ArrayList<String> foods = (ArrayList<String>) event.getFoodsOfEvent().stream().
                map(FoodEvent::getFood).collect(Collectors.toList());
        return new SubscribedEventDTORes(event.getEventId(),event.getTitle(),event.getHoliday(),event.getConfession(),
                    event.getDate(),event.getDescription(),event.getStatus(),getOwnerDTO(profile,event.getStatus()),
                event.getTime(),event.getDuration(),new AddressDTORes(event.getCity()),foods);
    }






    private OwnerDTORes getOwnerDTO(Profile profile,String status){
        ArrayList<String> pictures = (ArrayList<String>) profile.getPictures().stream().
                sorted((a,b)->a.compare(a,b)).map(Picture::getUrl).collect(Collectors.toList());
        ArrayList<String> foods = (ArrayList<String>) profile.getFoodsOfProfile().stream().
                map(FoodProfile::getFood).collect(Collectors.toList());
        ArrayList<String> lang = (ArrayList<String>) profile.getLanguagesOfProfile().stream().
                map(Languages::getLanguage).collect(Collectors.toList());
        if(status.equals("Pending")){
            return  new OwnerPhoneDTORes(profile.getFullName(),profile.getConfession(),profile.getGender(),
                    profile.getAge(),pictures,profile.getMaritalStatus(),foods,lang,profile.getRate(),
                    profile.getNumberOfVoters(),profile.getPhoneNumber());
        }
        return new OwnerVotersDTORes(profile.getFullName(),profile.getConfession(),profile.getGender(),profile.getAge(),
                pictures,profile.getMaritalStatus(),foods,lang,profile.getRate(),profile.getNumberOfVoters());
    }
}
