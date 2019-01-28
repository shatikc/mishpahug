package com.telran.mishpahug.repository.updateProfile;

import com.telran.mishpahug.entities.Languages;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface IUpdateLangCRUD extends CrudRepository<Languages,Object>{

    @Query("SELECT p FROM Languages p where p.language = :language")
    Languages getLanguage(String language);


    @Query("SELECT p.language FROM Languages p")
    ArrayList<String> getAllLangName();
}
