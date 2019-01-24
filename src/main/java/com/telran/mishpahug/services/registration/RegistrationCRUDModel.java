package com.telran.mishpahug.services.registration;

import com.telran.mishpahug.api.ResponseDTO.MessageDTORes;
import com.telran.mishpahug.entities.Profile;
import com.telran.mishpahug.entities.StaticFields;
import org.hibernate.validator.internal.constraintvalidators.bv.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Base64;
import java.util.Optional;


@Repository
public class RegistrationCRUDModel implements IRegistration {

    @Autowired
    com.telran.mishpahug.repository.IRegistration testdb;

    @Override
    @Transactional
    public ResponseEntity<Object> addNewUser(String headers) {
        String [] emailPass = parseHeaders(headers);
        if(emailPass.length<2||!validator(emailPass[0])||emailPass[1].length()<6){
            return new ResponseEntity<>(new MessageDTORes(422,"Invalid data!"), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        Optional<Profile> exist = testdb.findById(emailPass[0]);
        if(exist.isPresent()){return new ResponseEntity<>(new MessageDTORes(409,"User exists!"),HttpStatus.CONFLICT);}
        Profile prof = new Profile();
        prof.setEmail(emailPass[0]);
        prof.setPassword(emailPass[1]);
        testdb.save(prof);
        return new ResponseEntity<>(new StaticFields(),HttpStatus.OK);
    }

    private String [] parseHeaders(String headers){
        String [] arr = headers.split(",");
        String arrOfHeaders = arr[2].trim();
        String [] values = arrOfHeaders.split(" ");
        String tokenBase64 = values[1].replace("]","").trim();
        return new String(Base64.getDecoder().decode(tokenBase64.getBytes())).split(":");
    }

    private boolean validator(String email){
        if(email.equals("")){return false;}
        email = email.trim();
        return new EmailValidator().isValid(email, null);
    }

}
