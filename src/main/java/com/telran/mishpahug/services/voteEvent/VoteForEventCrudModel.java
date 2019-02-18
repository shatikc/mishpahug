package com.telran.mishpahug.services.voteEvent;

import com.telran.mishpahug.api.ResponseDTO.MessageDTORes;
import com.telran.mishpahug.entities.Event;
import com.telran.mishpahug.entities.Invited;
import com.telran.mishpahug.entities.Profile;
import com.telran.mishpahug.repository.voteForEvent.IVoteForEventCRUD;
import com.telran.mishpahug.repository.voteForEvent.IVoteProfileCRUD;
import com.telran.mishpahug.services.parser.IParser;
import org.apache.commons.math3.util.Precision;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.Set;

@Repository
public class VoteForEventCrudModel implements IVoteForEvent {

    @Autowired
    IVoteForEventCRUD voteRepo;

    @Autowired
    IVoteProfileCRUD voteProfile;

    @Autowired
    IParser parseVote;

    @Override
    @Transactional
    public ResponseEntity voteForEvent(String token64, long eventId, double vote) {
        String [] emailPass = parseVote.parseToken(token64);
        Profile profile = voteProfile.findProfile(emailPass[0], emailPass[1]);
        if(profile!=null){
            Event event = voteRepo.findEvent(eventId);
            if(event==null || !event.getStatus().equals("Done")||!putInvitedEvent(event)){return new ResponseEntity<>(
                    new MessageDTORes(409,"User has already voted" +
                                " for the event or can't vote for the event!"),HttpStatus.CONFLICT);
            }
            putInvitedEvent(event);
            putInvitedProfile(profile,event);
            putNewRateProfile(event.getOwner_email(),vote);
            voteRepo.save(event);
            voteProfile.save(profile);
            return new ResponseEntity<>(new MessageDTORes(200,"User vote is accepted!"),HttpStatus.OK);
        }
        return new ResponseEntity<>("{{Error_401_sample}}", HttpStatus.UNAUTHORIZED);
    }


    private boolean putInvitedEvent(Event event){
                Set<Invited> invited = event.getInvited();
                for (Invited element:invited) {
                    if(element.getEventId().getEventId()==event.getEventId()){
                        if(!element.isVoited()){
                            element.setVoited(true);
                            return true;
                        }
                    }
                }
                return false;
    }

    private void putInvitedProfile(Profile profile,Event event){
          Set<Invited> invited = profile.getInvited();
        for (Invited inv:invited) {
            if(inv.getEventId().getEventId()==event.getEventId()){
                inv.setVoited(true);
            }
        }
    }

    private void putNewRateProfile(Profile profile,double vote){
        double newRate = getRate(profile.getRate(),profile.getNumberOfVoters(),vote);
        profile.setRate(newRate);
        profile.setNumberOfVoters(profile.getNumberOfVoters()+1);
    }


    private double getRate(double existRate, int existNumOfVoters, double vote){
        double newRate = (existRate*existNumOfVoters+vote)/(existNumOfVoters+1);
         return Precision.round(newRate,2);
    }
}
