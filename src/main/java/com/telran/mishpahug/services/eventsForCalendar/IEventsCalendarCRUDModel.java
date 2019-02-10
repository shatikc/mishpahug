package com.telran.mishpahug.services.eventsForCalendar;

import com.telran.mishpahug.api.ResponseDTO.EventsListForCalendarDTORes.EventDTORes;
import com.telran.mishpahug.api.ResponseDTO.EventsListForCalendarDTORes.ListCalendarDTORes;
import com.telran.mishpahug.entities.Event;
import com.telran.mishpahug.entities.Profile;
import com.telran.mishpahug.repository.IEventsCalendarCRUD;
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
public class IEventsCalendarCRUDModel implements IEventsForCalendar {

    @Autowired
    IParser parseEventsCalendar;

    @Autowired
    IEventsCalendarCRUD calendarRepo;

    @Override
    @Transactional
    public ResponseEntity getEventsForCalendar(String token64, int month) {
        String [] emailPass = parseEventsCalendar.parseToken(token64);
        Profile profile = calendarRepo.findProfile(emailPass[0],emailPass[1]);
        if(profile!=null){
            if(month<=0||month>12){
                return new ResponseEntity<>("{{Error_422_parameter_sample}}",HttpStatus.UNPROCESSABLE_ENTITY);
            }
            List<Event> subscribed = getEventsByMonth(profile.getEvents(),month);
            List<Event> myEvents = getEventsByMonth(profile.getOwners(),month);
            return new ResponseEntity<>(new ListCalendarDTORes(getListDTO(myEvents),getListDTO(subscribed)),HttpStatus.OK);
        }
        return new ResponseEntity<>("{{Error_401_sample}}", HttpStatus.UNAUTHORIZED);
    }


    private List<Event> getEventsByMonth(Set<Event>events, int month){
        return events.stream().
                filter(x->!x.getStatus().equals("Done")).
                filter(x->x.getDate().getMonth().getValue()==month).
                sorted((a,b)->a.compare(a,b)).
                collect(Collectors.toList());
    }

    private ArrayList<EventDTORes> getListDTO(List<Event>list){
        ArrayList<EventDTORes> res = new ArrayList<>();
        for (Event event:list) {
            res.add(getEventDTORes(event));
        }
        return res;
    }

    private EventDTORes getEventDTORes(Event event){
        return new EventDTORes(event.getEventId(),event.getTitle(),event.getDate(),event.getTime(),event.getDuration(),
                event.getStatus());
    }

}
