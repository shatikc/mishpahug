package com.telran.mishpahug.repository.updateProfile;

import com.telran.mishpahug.entities.Picture;
import com.telran.mishpahug.entities.Profile;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IUpdatePicCRUD extends CrudRepository<Picture,Object> {

    @Query("DELETE FROM  Picture P where P.owner_email = :email")
    @Modifying
    void deleteAllOwnerEmail(Profile email);

}
