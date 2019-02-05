package com.telran.mishpahug.repository.AddEvent;
import com.telran.mishpahug.entities.FoodEvent;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IaddEventFoodCRUD extends CrudRepository<FoodEvent,Object> {

    @Query("SELECT p FROM FoodEvent p where p.food = :foodName")
    FoodEvent getFoodEvent(String foodName);

    @Query("SELECT p FROM FoodEvent p")
    List<FoodEvent> getFoodEventList();
}
