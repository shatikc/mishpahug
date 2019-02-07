package com.telran.mishpahug.repository.voteForEvent;

import com.telran.mishpahug.entities.Event;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IVoteForEventCRUD extends CrudRepository<Event,Object> {

    @Query("SELECT e FROM Event e where e.eventId = :eventId")
    Event findEvent(long eventId);
}
