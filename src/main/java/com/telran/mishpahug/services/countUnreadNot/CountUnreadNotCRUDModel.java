package com.telran.mishpahug.services.countUnreadNot;

import com.telran.mishpahug.api.ResponseDTO.CountUnreadNotDTORes;
import com.telran.mishpahug.entities.Profile;
import com.telran.mishpahug.repository.ICountUnreadNotCRUD;
import com.telran.mishpahug.services.parser.IParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class CountUnreadNotCRUDModel implements ICountUnreadNot {

    @Autowired
    ICountUnreadNotCRUD countRepo;

    @Autowired
    IParser parseCount;

    @Override
    @Transactional
    public ResponseEntity getCountUnreadNotifications(String token64) {
        String [] emailPass = parseCount.parseToken(token64);
        Profile profile = countRepo.findProfile(emailPass[0], emailPass[1]);
        if(profile!=null){
            long count = profile.getNotifications().stream().filter(x->x.isRead()==false).count();
            return new ResponseEntity<>(new CountUnreadNotDTORes(count),HttpStatus.OK);
        }
        return new ResponseEntity<>("{{Error_401_sample}}", HttpStatus.UNAUTHORIZED);
    }

}
