package com.telran.mishpahug.services.updateProfile;
import com.telran.mishpahug.api.RequestsDTO.UpdateProfileDTO;
import com.telran.mishpahug.api.ResponseDTO.LoginUserDTORes;
import com.telran.mishpahug.api.ResponseDTO.MessageDTORes;
import com.telran.mishpahug.entities.FoodProfile;
import com.telran.mishpahug.entities.Languages;
import com.telran.mishpahug.entities.Picture;
import com.telran.mishpahug.entities.Profile;
import com.telran.mishpahug.repository.updateProfile.IUpdateFoodCRUD;
import com.telran.mishpahug.repository.updateProfile.IUpdateLangCRUD;
import com.telran.mishpahug.repository.updateProfile.IUpdatePicCRUD;
import com.telran.mishpahug.repository.updateProfile.IUpdateProfileCRUD;
import com.telran.mishpahug.services.parser.IParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

@Repository
public class UpdateCRUDModel implements IUpdateProfile {
    @Autowired
    IUpdateProfileCRUD updateRepo;

    @Autowired
    IUpdateFoodCRUD updateFood;

    @Autowired
    IUpdateLangCRUD updateLang;

    @Autowired
    IUpdatePicCRUD updatePic;

    @Autowired
    IParser updateParseToken;

    @Override
    @Transactional
    public ResponseEntity updateProfile(String token, UpdateProfileDTO data) {
        String [] emailPass = updateParseToken.parseToken(token);
        Profile profile = updateRepo.findProfile(emailPass[0], emailPass[1]);
        if(profile==null){return new ResponseEntity<>("{{Error_401_sample}}", HttpStatus.UNAUTHORIZED);}
        if(!validateProfUpdate(data)){
            return new ResponseEntity<>(new MessageDTORes(422,"User data is invalid!"),HttpStatus.UNPROCESSABLE_ENTITY);
        }
        setAllNewFieldsOfProfile(profile, data);
        updateRepo.save(profile);
        LoginUserDTORes response = getLoginUserDTO(profile, data);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    private void setAllNewFieldsOfProfile(Profile profile, UpdateProfileDTO data){
        profile.setFirstName(data.getFirstName());
        profile.setLastName(data.getLastName());
        profile.setDateOfBirth(LocalDate.parse(data.getDateOfBirth()));
        profile.setGender(data.getGender());
        profile.setMaritalStatus(data.getMaritalStatus());
        profile.setConfession(data.getConfession());
        profile.setPhoneNumber(data.getPhoneNumber());
        profile.setDescription(data.getDescription());
        profile.setPictures(setNewPictures(data.getPictureLink(),profile));
        Set<FoodProfile> setProf = setNewFood(data.getFoodPreferences(), profile);
        profile.setFoodsOfProfile(setProf);
        Set<Languages> setLang = setNewLang(data.getLanguages(),profile);
        profile.setLanguagesOfProfile(setLang);
    }

    private boolean validateProfUpdate(UpdateProfileDTO data){
        boolean isFirstName = Pattern.matches("\\p{Alpha}+",data.getFirstName());
        boolean isLastName =  Pattern.matches("\\p{Alpha}+",data.getLastName());
        boolean isPhoneNumber = Pattern.matches("\\d+",data.getPhoneNumber());
        boolean isDateOfBirth = isLocalDate(data.getDateOfBirth());
        if(isFirstName){ if(isLastName){ if(isPhoneNumber){ return isDateOfBirth; } } }
        return false;
    }

    private boolean isLocalDate(String date){
        try { LocalDate.parse(date);}
        catch (DateTimeParseException e) { return false;}
        return true;
    }


    private Set<Picture> setNewPictures(ArrayList<String> pictureLink, Profile owner){
        Set<Picture> picturesDB= new HashSet<>();
        int count = 0;
        for (String el:pictureLink) {
            if(count==0){picturesDB.add(new Picture(el,true,owner));count++;}
            else{ picturesDB.add(new Picture(el,false,owner));}
        }
        updatePic.deleteAllOwnerEmail(owner);
        updatePic.saveAll(picturesDB);
        return picturesDB;
    }

    private Set<FoodProfile> setNewFood(ArrayList<String> foodPref, Profile profile){
        deleteAllOldFoodsByEmail(foodPref,profile);
        Set<FoodProfile> foods = new HashSet<>();
        for (String food : foodPref) {
            FoodProfile item = updateFood.getFoodProfile(food);
            if(item!=null){
                Set<Profile> profiles = item.getProfilesOfFood();
                profiles.add(profile);
            }else {
                Set<Profile> profiles = new HashSet<>();
                profiles.add(profile);
                item = new FoodProfile(food,profiles);
            }
            updateFood.save(item);
            foods.add(item);
        }
        return foods;
    }

    private void deleteAllOldFoodsByEmail(ArrayList<String> foods ,Profile profile){
        ArrayList<String> allFoodNames = updateFood.getAllFoodName();
        for (String el:allFoodNames) {
            if(!foods.contains(el)){
                FoodProfile item = updateFood.getFoodProfile(el);
                Set<Profile> profiles = item.getProfilesOfFood();
                saveOrDelete(profiles, profile, item, updateFood);
            }
        }
    }


    private void deleteAllOldLangByEmail(ArrayList<String>lang, Profile profile){
        ArrayList<String> allLangNames = updateLang.getAllLangName();
        for (String el: allLangNames) {
            if(!lang.contains(el)){
                Languages item = updateLang.getLanguage(el);
                Set<Profile> prof = item.getProfilesOfLanguage();
                saveOrDelete(prof, profile, item, updateLang);
            }
        }
    }

    private void saveOrDelete(Set<Profile>profiles, Profile profile, Object item, CrudRepository crudRepository){
        if(profiles.contains(profile)){
            profiles.remove(profile);
            if(profiles.size()==0){crudRepository.delete(item);}
            else{crudRepository.save(item);}
        }
    }

    private Set<Languages> setNewLang(ArrayList<String> lang,Profile profile){
        deleteAllOldLangByEmail(lang, profile);
        Set<Languages> res = new HashSet<>();
        for (String el:lang) {
            Languages item = updateLang.getLanguage(el);
            if(item!=null){
                Set<Profile> languages= item.getProfilesOfLanguage();
                languages.add(profile);
            }else{
                Set<Profile> prof = new HashSet<>();
                prof.add(profile);
                item = new Languages(el,prof);
            }
            updateLang.save(item);
            res.add(item);
        }
        return res;
    }


    private LoginUserDTORes getLoginUserDTO(Profile profile,UpdateProfileDTO element){
        return new LoginUserDTORes(profile.getFirstName(),profile.getLastName(),profile.getDateOfBirth(),
                profile.getGender(),profile.getMaritalStatus(),profile.getConfession(),
                element.getPictureLink(),profile.getPhoneNumber(),
                element.getFoodPreferences(),element.getLanguages(),profile.getDescription(),
                profile.getRate(),profile.getNumberOfVoters());
    }
}
