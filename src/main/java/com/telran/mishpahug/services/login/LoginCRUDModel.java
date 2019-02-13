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
        if(profile==null){return new ResponseEntity<>(new MessageDTORes(401,"Wrong login or password!"),
                HttpStatus.UNAUTHORIZED);}
        if(!isProfileFilled(profile)){
            return new ResponseEntity<>(new MessageDTORes(409,"User has empty profile!"),
                    HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(getResponseDTO(profile), HttpStatus.OK);
    }

    private boolean isProfileFilled(Profile profile){
        Set<FoodProfile> foods = profile.getFoodsOfProfile();
        Set<Languages> languages = profile.getLanguagesOfProfile();
        if(profile.getConfession()==null||profile.getGender()==null||profile.getMaritalStatus()==null||
                foods==null||languages==null||profile.getDescription()==null||profile.getFirstName()==null
        ||profile.getLastName()==null||profile.getPhoneNumber()==null||profile.getDateOfBirth()==null){
            return false;
        }
        return true;
    }

    private LoginUserDTORes getResponseDTO(Profile profile){
        return new LoginUserDTORes(profile.getFirstName(),profile.getLastName(),
                profile.getDateOfBirth(),profile.getGender(), profile.getMaritalStatus(),
                profile.getConfession(),sortPictures(profile.getPictures()),profile.getPhoneNumber(),
                getFoodStrings(profile.getFoodsOfProfile()),getLanguagesStrings(profile.getLanguagesOfProfile()),
                profile.getDescription(), profile.getRate(), profile.getNumberOfVoters());
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
