package com.telran.mishpahug.repository;
import com.telran.mishpahug.entities.Profile;
import org.springframework.data.repository.CrudRepository;

public interface IHug extends CrudRepository <Profile, Object> {

}
