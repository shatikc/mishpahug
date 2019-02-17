package com.telran.mishpahug.repository;

import com.telran.mishpahug.entities.Event;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IEventCleanerRepository extends CrudRepository<Event,Object> {

    @Query("SELECT p from Event p")
    List<Event> getAllEvents();

}
