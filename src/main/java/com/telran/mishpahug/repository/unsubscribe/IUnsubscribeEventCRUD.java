package com.telran.mishpahug.repository.unsubscribe;

import com.telran.mishpahug.entities.Event;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IUnsubscribeEventCRUD extends CrudRepository<Event,Object> {

    @Query("SELECT p FROM Event p WHERE p.eventId = :eventId")
    Event findEvent(long eventId);
}
