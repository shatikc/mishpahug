package com.telran.mishpahug.services.login;
import com.telran.mishpahug.api.ResponseDTO.LoginUserDTORes;
import com.telran.mishpahug.api.ResponseDTO.MessageDTORes;
import com.telran.mishpahug.entities.*;
import com.telran.mishpahug.repository.ILoginCRUD;
import com.telran.mishpahug.services.parser.IParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class LoginCRUDModel implements ILogin {

    @Autowired
    ILoginCRUD logRepo;

    @Autowired
    IParser parserLog;


    @Override
    @Transactional
    public ResponseEntity loginUser(String token) {
        String [] emailPass = parserLog.parseToken(token);
        Profile profile = logRepo.findProfile(emailPass[0], emailPass[1]);
        if(profile==null){return new ResponseEntity<>(new MessageDTORes(401,"Wrong login or password!"), HttpStatus.UNAUTHORIZED);}
        Set<FoodProfile> foods = profile.getFoodsOfProfile();
        Set<Languages> languages = profile.getLanguagesOfProfile();
        Set<Holiday> holidays = profile.getHolidaysOfProfile();
        if(profile.getConfession()==null||profile.getGender()==null||profile.getMaritalStatus()==null||
                foods==null||languages==null||holidays==null){
            return new ResponseEntity<>(new MessageDTORes(409,"User has empty profile!"),HttpStatus.CONFLICT);
        }
        LoginUserDTORes response = new LoginUserDTORes(profile.getFirstName(),profile.getLastName(),
                profile.getDateOfBirth(),profile.getGender(), profile.getMaritalStatus(),
                profile.getConfession(),sortPictures(profile.getPictures()),profile.getPhoneNumber(),
                getFoodStrings(profile.getFoodsOfProfile()),getLanguagesStrings(profile.getLanguagesOfProfile()),
                profile.getDescription(), profile.getRate(), profile.getNumberOfVoters());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private ArrayList<String>sortPictures(Set<Picture> pictures){
        return (ArrayList<String>) pictures.stream().
                sorted((a, b)->a.compare(a,b)).
                map(Picture::getUrl).
                collect(Collectors.toList());
    }

    private ArrayList<String> getFoodStrings(Set<FoodProfile>foods){
        return (ArrayList<String>) foods.stream().map(FoodProfile::getFood).collect(Collectors.toList());
    }

    private ArrayList<String> getLanguagesStrings(Set<Languages>languages){
        return (ArrayList<String>) languages.stream().map(Languages::getLanguage).collect(Collectors.toList());
    }
}
