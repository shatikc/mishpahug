package com.telran.mishpahug.services.event;

import com.telran.mishpahug.api.RequestsDTO.AddEventDTO.AddEventDTO;
import com.telran.mishpahug.api.ResponseDTO.MessageDTORes;
import com.telran.mishpahug.entities.Event;
import com.telran.mishpahug.entities.FoodEvent;
import com.telran.mishpahug.entities.Profile;
import com.telran.mishpahug.repository.AddEvent.IAddEventCRUD;
import com.telran.mishpahug.repository.AddEvent.IAddEventProfileCRUD;
import com.telran.mishpahug.repository.AddEvent.IaddEventFoodCRUD;
import com.telran.mishpahug.services.parser.IParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class AddEventCRUDModel implements IAddEvent {

    @Autowired
    IParser parseAddEvent;

    @Autowired
    IAddEventCRUD addEventRepo;

    @Autowired
    IAddEventProfileCRUD addEventPro;

    @Autowired
    IaddEventFoodCRUD addEventFood;


    @Override
    @Transactional
    public ResponseEntity createNewEvent(String token64, AddEventDTO event) {
        String [] emailPass = parseAddEvent.parseToken(token64);
        Profile profile = addEventPro.findProfile(emailPass[0],emailPass[1]);
        if(profile!=null){
            if(!validateInput(event)||!isNotBusyDate(LocalDate.parse(event.getDate()))){
                return new ResponseEntity<>(new MessageDTORes(422,"Invalid data!"),
                        HttpStatus.UNPROCESSABLE_ENTITY);}
            LocalDate date = LocalDate.parse(event.getDate()); LocalTime time = LocalTime.parse(event.getTime());
            if(!isNotCoveredOtherEvents(date,time,profile,event.getDuration())){
                return new ResponseEntity<>(new MessageDTORes(409, "This user has already created the" +
                        " event on this date and time!"),HttpStatus.CONFLICT);
            }
                   Event newEvent = getNewEvent(event,profile);
                   Set<FoodEvent>foods = getFoodFromString(event.getFood(),newEvent);
                   addEventRepo.save(newEvent);
                   addEventFood.saveAll(foods);
                   return new ResponseEntity<>(new MessageDTORes(200,"Event is created"),
                           HttpStatus.OK);
        }
        return new ResponseEntity<>("{{Error_401_sample}}",HttpStatus.UNAUTHORIZED);
    }


    private Event getNewEvent(AddEventDTO eventDTO, Profile profile){
        Event response = new Event();
        response.setTitle(eventDTO.getTitle());
        response.setHoliday(eventDTO.getHoliday());
        response.setConfession(eventDTO.getConfession());
        response.setDate(LocalDate.parse(eventDTO.getDate()));
        response.setTime(LocalTime.parse(eventDTO.getTime()));
        response.setDuration(eventDTO.getDuration());
        response.setDescription(eventDTO.getDescription());
        response.setPlace_id(eventDTO.getAddress().getPlace_id());
        response.setCity(eventDTO.getAddress().getCity());
        response.setLat(eventDTO.getAddress().getLocation().getLat());
        response.setLng(eventDTO.getAddress().getLocation().getLng());
        response.setFoodsOfEvent(getFoodFromString(eventDTO.getFood(),response));
        response.setStatus("In progress");
        response.setOwner_email(profile);
        return response;
    }

    private Set<FoodEvent> getFoodFromString(ArrayList<String> foods,Event event){
        Set<Event>eventsForFood = new HashSet<>();
        List<String> foodFromDB = addEventFood.getFoodEventList().stream().
                map(FoodEvent::getFood).collect(Collectors.toList());
        Set<FoodEvent> res = new HashSet<>();
        for (String food:foods) {
            if(foodFromDB.contains(food)){
                FoodEvent object = addEventFood.getFoodEvent(food);
                Set<Event> set = object.getEventsOfFood();
                set.add(event);
                res.add(object);
            }else{
                eventsForFood.add(event);
                FoodEvent item = new FoodEvent(food,eventsForFood);
                res.add(item);
            }
        }
        return res;
    }

    private boolean isNotBusyDate(LocalDate date){
        LocalDate toDay = LocalDate.now();
        if(date.isBefore(toDay)){return false;}
        LocalDate lastDay = LocalDate.now().plusDays(1);
        return date.isAfter(lastDay)||toDay.isEqual(lastDay);
    }

    private boolean isNotCoveredOtherEvents(LocalDate date, LocalTime time,Profile profile, int duration){
        Set<Event> events = profile.getOwners();
        if(events.size()==0){return true;}
        for (Event element:events) {
            if(element.getDate().isEqual(date)){
                 LocalTime startTime1 = element.getTime();
                 LocalTime endTime1 = startTime1.plusMinutes(element.getDuration());
                if(time.isAfter(startTime1)&&time.isBefore(endTime1)){return false;}
                LocalTime endOfTime = time.plusMinutes(duration);
                if(endOfTime.isAfter(startTime1)&&endOfTime.isBefore(endTime1)){return false;}
                if(time.isBefore(startTime1)&&endOfTime.isAfter(endTime1)){return false;}
                if(time.equals(startTime1)){return false;}
            }
        }
        return true;
    }

    private boolean validateInput(AddEventDTO event){
        if(event.getHoliday()==null||event.getConfession()==null||event.getFood()==null
        ||event.getAddress()==null||event.getDate()==null||event.getTime()==null||event.getTitle()==null
        ||event.getDescription()==null||event.getDuration()==0){ return false;}
        boolean isDate = isLocalDate(event.getDate());
        boolean isTime = isLocalTime(event.getTime());
        boolean isTitle = event.getTitle().length()<21;
        boolean isDescription = event.getDescription().length()<301;
        boolean isDuration = event.getDuration()<2881;
        if(isDate){ if(isTime){ if(isTitle){ if(isDescription){ return isDuration; } } } }
        return false;
    }

    private boolean isLocalDate(String date){
        try { LocalDate.parse(date);}
        catch (DateTimeParseException e) { return false;}
        return true;
    }

    private boolean isLocalTime(String time){
        try { LocalTime.parse(time);}
        catch (DateTimeParseException e) {return false;}
        return true;
    }

}
