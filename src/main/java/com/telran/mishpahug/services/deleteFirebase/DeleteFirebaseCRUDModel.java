package com.telran.mishpahug.services.deleteFirebase;

import com.telran.mishpahug.api.RequestsDTO.TokenDTO;
import com.telran.mishpahug.api.ResponseDTO.MessageDTORes;
import com.telran.mishpahug.entities.Profile;
import com.telran.mishpahug.repository.IDeleteFirebaseCRUD;
import com.telran.mishpahug.services.parser.IParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class DeleteFirebaseCRUDModel implements IDeleteFirebase {

    @Autowired
    IDeleteFirebaseCRUD deleteFbRepo;

    @Autowired
    IParser parserFB;

    @Override
    @Transactional
    public ResponseEntity deleteToken(String token64, TokenDTO data) {
        String [] emailPass = parserFB.parseToken(token64);
        Profile prof = deleteFbRepo.findProfile(emailPass[0],emailPass[1]);
        if(prof!=null){
            String tokenFromDB = prof.getFirebaseToken();
            String tokenFromUser = data.getFirebaseToken();
            if(tokenFromDB.equals(tokenFromUser)){
                prof.setFirebaseToken(null);
                deleteFbRepo.save(prof);
                return new ResponseEntity<>(new MessageDTORes(200, "Token is deleted!"),HttpStatus.OK);
            }else{
                return new ResponseEntity<>
                        (new MessageDTORes(409,"User is not associated with the token!"),HttpStatus.CONFLICT);
            }
        }
        return new ResponseEntity<>("{{Error_401_sample}}",HttpStatus.UNAUTHORIZED);
    }
}
