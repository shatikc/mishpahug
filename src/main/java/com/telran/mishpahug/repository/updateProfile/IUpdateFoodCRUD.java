package com.telran.mishpahug.repository.updateProfile;

import com.telran.mishpahug.entities.FoodProfile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface IUpdateFoodCRUD extends CrudRepository<FoodProfile,Object> {
    @Query("SELECT p FROM FoodProfile p where p.food = :foodName")
    FoodProfile getFoodProfile(String foodName);


    @Query("SELECT p.food FROM FoodProfile  p")
    ArrayList<String> getAllFoodName();
}
