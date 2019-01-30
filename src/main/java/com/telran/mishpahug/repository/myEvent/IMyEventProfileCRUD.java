package com.telran.mishpahug.repository.myEvent;

import com.telran.mishpahug.entities.Profile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IMyEventProfileCRUD extends CrudRepository<Profile, Object> {

    @Query("SELECT p FROM Profile p where p.email = :email and p.password = :password")
    Profile findProfile(String email, String password);

    @Query("SELECT p FROM Profile p")
    List<Profile> getAllprofiles();
}
