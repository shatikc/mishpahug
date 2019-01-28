package com.telran.mishpahug.services.addFirebase;

import com.telran.mishpahug.api.RequestsDTO.TokenDTO;
import com.telran.mishpahug.api.ResponseDTO.MessageDTORes;
import com.telran.mishpahug.entities.Profile;
import com.telran.mishpahug.repository.IAddFirebaseCRUD;
import com.telran.mishpahug.services.parser.IParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public class AddFirebaseCRUDModel implements IAddFirebase {

    @Autowired
    IAddFirebaseCRUD addFbRepo;

    @Autowired
    IParser parserFirebase;

    @Override
    @Transactional
    public ResponseEntity addToken(String headers, TokenDTO token) {
        String [] emailPass = parserFirebase.parseToken(headers);
        Profile profile = addFbRepo.findProfile(emailPass[0], emailPass[1]);
        if(profile!=null){
            profile.setFirebaseToken(token.getFirebaseToken());
            addFbRepo.save(profile);
            return new ResponseEntity<>(new MessageDTORes(200, "Token is added!"),HttpStatus.OK);
        }
        return new ResponseEntity<>("{{Error_401_sample}}", HttpStatus.UNAUTHORIZED);
    }
}
