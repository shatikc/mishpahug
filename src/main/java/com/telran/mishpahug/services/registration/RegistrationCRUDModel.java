package com.telran.mishpahug.services.registration;

import com.telran.mishpahug.api.ResponseDTO.MessageDTORes;
import com.telran.mishpahug.entities.Profile;
import com.telran.mishpahug.entities.StaticFields;
import com.telran.mishpahug.repository.IRegistrationCRUD;
import com.telran.mishpahug.services.parser.IParser;
import org.hibernate.validator.internal.constraintvalidators.bv.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;


@Repository
public class RegistrationCRUDModel implements IRegistration {

    @Autowired
    IRegistrationCRUD regRepo;

    @Autowired
    IParser parserReg;

    @Override
    @Transactional
    public ResponseEntity addNewUser(String token) {
        String [] emailPass = parserReg.parseToken(token);
        if(emailPass.length<2||!validator(emailPass[0])||emailPass[1].length()<6){
            return new ResponseEntity<>(new MessageDTORes(422,"Invalid data!"), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        Optional exist = regRepo.findById(emailPass[0]);
        if(exist.isPresent()){return new ResponseEntity<>(new MessageDTORes(409,"User exists!"),HttpStatus.CONFLICT);}
        Profile prof = new Profile();
        prof.setEmail(emailPass[0]);
        prof.setPassword(emailPass[1]);
        regRepo.save(prof);
        return new ResponseEntity<>(new StaticFields(),HttpStatus.OK);
    }


    private boolean validator(String email){
        if(email.equals("")){return false;}
        email = email.trim();
        return new EmailValidator().isValid(email, null);
    }

}
