package com.telran.mishpahug.services;

import com.telran.mishpahug.entities.Event;
import com.telran.mishpahug.entities.Profile;
import com.telran.mishpahug.repository.IEventCleanerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.time.*;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


@Component
public class EventCleaner extends Thread {

    @Autowired
    IEventCleanerRepository cleanerRepo;


    @Override
    @Transactional
    public void run() {
        while(true) {
            checkEventsNotDone();
            checkEventsDone();
            try {
                Thread.sleep(TimeUnit.HOURS.toMillis(12));
            } catch (InterruptedException e) {
                return;
            }
        }
    }

    private void checkEventsDone(){
        ZonedDateTime criticalPoint = ZonedDateTime.of(LocalDateTime.now(), ZoneId.of("Asia/Jerusalem"));
        List<Event> list = getListOfEventToChangeStatusToDone(criticalPoint);
        for (Event event:list) {
                sendMessagesToSubscribers(event,"");
                event.setStatus("Done");
                cleanerRepo.save(event);
        }

    }

    private List<Event> getListOfEventToChangeStatusToDone(ZonedDateTime criticalPoint){
        return cleanerRepo.getEventByStatus("Pending").stream().
                filter(x->getEndPointOfEvent(x).isBefore(LocalDateTime.from(criticalPoint))).collect(Collectors.toList());
    }

    private LocalDateTime getEndPointOfEvent(Event event){
        return LocalDateTime.of(event.getDate(),event.getTime()).plusMinutes(event.getDuration());
    }


    private void checkEventsNotDone(){
        ZonedDateTime criticalPoint = ZonedDateTime.of(LocalDateTime.now(), ZoneId.of("Asia/Jerusalem")).plusHours(24);
        List<Event> events = getListOfEventToChangeStatusToNotDone(criticalPoint);
            for(Event event : events){
                sendMessagesToSubscribers(event,"");
                event.setStatus("Not done");
                cleanerRepo.save(event);
            }
    }


    private  List<Event> getListOfEventToChangeStatusToNotDone(ZonedDateTime criticalPoint){
        return cleanerRepo.getEventByStatus("In progress").
                stream().filter(x->x.getDate().isBefore(LocalDate.from(criticalPoint))
                ||x.getDate().isEqual(LocalDate.from(criticalPoint))
                &&x.getTime().isBefore(LocalTime.from(criticalPoint))).collect(Collectors.toList());
    }


    private void sendMessagesToSubscribers(Event event,String message){
        Set<Profile> subscribers = event.getSubscribers();
        for (Profile prof: subscribers) {
            //Here we have to send some message about the cancel of the event.
            //After learning how to work with firebase notifications.
            //TODO
        }
    }
}
