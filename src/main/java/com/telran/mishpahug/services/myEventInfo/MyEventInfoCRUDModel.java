package com.telran.mishpahug.services.myEventInfo;

import com.telran.mishpahug.api.ResponseDTO.MessageDTORes;
import com.telran.mishpahug.api.ResponseDTO.MyEventInfoDTORes.MyEventDTORes;
import com.telran.mishpahug.entities.Event;
import com.telran.mishpahug.entities.Profile;
import com.telran.mishpahug.repository.myEvent.IMyEventFoodCRUD;
import com.telran.mishpahug.repository.myEvent.IMyEventInfoCRUD;
import com.telran.mishpahug.repository.myEvent.IMyEventProfileCRUD;
import com.telran.mishpahug.services.parser.IParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



//IS NOT FINISHED!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

@Repository
public class MyEventInfoCRUDModel implements IMyEventInfo {

    @Autowired
    IMyEventFoodCRUD eventAddFood;

    @Autowired
    IMyEventInfoCRUD eventInfoRepo;

    @Autowired
    IMyEventProfileCRUD eventCheckProf;

    @Autowired
    IParser parseEvent;

    @Override
    @Transactional
    public ResponseEntity getEventInfo(String token64, long eventId) {
        String[] emailPass = parseEvent.parseToken(token64);
        Profile profile = eventCheckProf.findProfile(emailPass[0], emailPass[1]);
        if (profile != null) {
            Event event = eventInfoRepo.findEvent(eventId);
            if (event != null) {
                return new ResponseEntity<>(event, HttpStatus.OK);
            }
                return new ResponseEntity<>(new MessageDTORes(409,"User is not associated with the event!"),
                        HttpStatus.UNPROCESSABLE_ENTITY);
            }
            return new ResponseEntity<>("{{Error_401_sample}}", HttpStatus.UNAUTHORIZED);
        }


        private MyEventDTORes setMyEventDTORes(Event event){

         return null;
        }

    }
