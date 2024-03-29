package com.telran.mishpahug.repository.changeEventStatus;

import com.telran.mishpahug.entities.Event;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IChangeEventStatusCRUD extends CrudRepository<Event,Long> {

    @Query("SELECT p FROM Event p WHERE p.eventId = :eventId")
    Event findEvent(long eventId);
}
