package com.telran.mishpahug.repository.updateProfile;
import com.telran.mishpahug.entities.Profile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface IUpdateProfileCRUD extends CrudRepository <Profile,Object>{

    @Query("SELECT p FROM Profile p where p.email = :email and p.password = :password")
    Profile findProfile(String email, String password);

}
