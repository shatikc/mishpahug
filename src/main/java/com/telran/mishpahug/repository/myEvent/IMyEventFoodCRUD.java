package com.telran.mishpahug.repository.myEvent;

import com.telran.mishpahug.entities.FoodEvent;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IMyEventFoodCRUD extends CrudRepository<FoodEvent,Object> {

    @Query("SELECT p FROM FoodEvent p WHERE p.food = :name")
    FoodEvent getFood(String name);

}
