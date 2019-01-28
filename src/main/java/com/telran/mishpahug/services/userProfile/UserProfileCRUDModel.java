package com.telran.mishpahug.services.userProfile;

import com.telran.mishpahug.api.ResponseDTO.LoginUserDTORes;
import com.telran.mishpahug.api.ResponseDTO.MessageDTORes;
import com.telran.mishpahug.entities.FoodProfile;
import com.telran.mishpahug.entities.Languages;
import com.telran.mishpahug.entities.Picture;
import com.telran.mishpahug.entities.Profile;
import com.telran.mishpahug.repository.IUserProfileCRUD;
import com.telran.mishpahug.services.parser.IParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Repository
public class UserProfileCRUDModel implements IUserProfile {

    @Autowired
    IUserProfileCRUD userRepo;

    @Autowired
    IParser parseUser;

    @Override
    @Transactional
    public ResponseEntity getUser(String token) {
        String [] emailPass = parseUser.parseToken(token);
        Profile profile = userRepo.findProfile(emailPass[0], emailPass[1]);
        if(profile!=null){
            if(checkStatusOfUser(profile)){
                LoginUserDTORes response = setAllFieldsForResponse(profile);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(new MessageDTORes(409,"User has empty profile!"),HttpStatus.CONFLICT);
            }
        }
        return new ResponseEntity<>("{{Error_401_sample}}",HttpStatus.UNAUTHORIZED);
    }

    private boolean checkStatusOfUser(Profile profile){
        if(profile.getFirstName()==null||profile.getLastName()==null||profile.getPhoneNumber()==null||
        profile.getConfession()==null||profile.getDateOfBirth()==null||profile.getMaritalStatus()==null
        ||profile.getFoodsOfProfile()==null||profile.getGender()==null||profile.getLanguagesOfProfile()==null
        ||profile.getDescription()==null){return false;}
        return true;
    }

    private LoginUserDTORes setAllFieldsForResponse(Profile profile){
       ArrayList<String>pictures = (ArrayList<String>) profile.getPictures().stream().
                sorted((a, b)->a.compare(a,b)).
                map(Picture::getUrl).collect(Collectors.toList());
       ArrayList<String> foods = (ArrayList<String>) profile.getFoodsOfProfile().stream().
               map(FoodProfile::getFood).collect(Collectors.toList());
       ArrayList<String> languages = (ArrayList<String>) profile.getLanguagesOfProfile().stream().
               map(Languages::getLanguage).collect(Collectors.toList());
        return new LoginUserDTORes(profile.getFirstName(), profile.getLastName(), profile.getDateOfBirth(),
                profile.getGender(),profile.getMaritalStatus(), profile.getConfession(), pictures,profile.getPhoneNumber(),
        foods,languages,profile.getDescription(),profile.getRate(),profile.getNumberOfVoters());
    }
}
