package com.telran.mishpahug.repository.unauthorizedList;

import com.telran.mishpahug.entities.Event;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface IListUnauthorizedCRUD extends CrudRepository<Event,Object> {

     @Query
     List<Event> getEventsByDateBetweenAndStatus(LocalDate from, LocalDate to,String status);

}
