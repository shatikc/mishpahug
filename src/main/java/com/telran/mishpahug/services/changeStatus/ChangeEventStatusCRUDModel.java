package com.telran.mishpahug.services.changeStatus;

import com.telran.mishpahug.api.ResponseDTO.MessageDTORes;
import com.telran.mishpahug.entities.Event;
import com.telran.mishpahug.entities.Profile;
import com.telran.mishpahug.repository.changeEventStatus.IChangeEventStatusCRUD;
import com.telran.mishpahug.repository.changeEventStatus.IChangeProfileStatusCRUD;
import com.telran.mishpahug.services.parser.IParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.Set;

@Repository
public class ChangeEventStatusCRUDModel implements IChangeEventStatus {

    @Autowired
    IParser parseStatus;

    @Autowired
    IChangeEventStatusCRUD changeRepo;

    @Autowired
    IChangeProfileStatusCRUD changeProf;

    @Override
    @Transactional
    public ResponseEntity changeEventStatus(String token64, long eventId) {
        String [] emailPass = parseStatus.parseToken(token64);
        Profile profile = changeProf.findProfile(emailPass[0], emailPass[1]);
        if(profile!=null){
            Event event = changeRepo.findEvent(eventId);
            if(event!=null&&event.getOwner_email().equals(profile)){
                if(!event.getStatus().equals("Pending")){
                    putStatusInProfile(profile,eventId);
                    event.setStatus("Pending");
                    changeProf.save(profile);
                    changeRepo.save(event);
                    return new ResponseEntity<>(new MessageDTORes(eventId,"Pending"),HttpStatus.OK);
                }
            }
            return new ResponseEntity<>(new MessageDTORes(409,"User is not associated with the " +
                    "event!"),HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("{{Error_401_sample}}", HttpStatus.UNAUTHORIZED);
    }



    private void putStatusInProfile(Profile profile,long eventId){
        Set<Event> eventsOfProfile = profile.getOwners();
        for (Event event:eventsOfProfile) {
            if(event.getEventId()==eventId){ event.setStatus("Pending");}
        }
    }
}
