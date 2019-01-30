package com.telran.mishpahug.repository.myEvent;

import com.telran.mishpahug.entities.Event;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IMyEventInfoCRUD extends CrudRepository<Event, Object> {

    @Query("SELECT p FROM Event p WHERE p.eventId = :eventId")
    Event findEvent(long eventId);


    @Query("SELECT p FROM Event p")
    List<Event> getAllEvents();

}
