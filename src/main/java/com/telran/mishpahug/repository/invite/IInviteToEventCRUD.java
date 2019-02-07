package com.telran.mishpahug.repository.invite;

import com.telran.mishpahug.entities.Event;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IInviteToEventCRUD extends CrudRepository<Event,Object> {

    @Query("SELECT p FROM Event p WHERE p.eventId = :eventId")
    Event findEvent(long eventId);
}
