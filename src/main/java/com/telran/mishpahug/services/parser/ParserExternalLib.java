package com.telran.mishpahug.services.parser;

import com.telran.mishpahug.entities.FoodProfile;
import com.telran.mishpahug.entities.Languages;
import com.telran.mishpahug.entities.Picture;
import com.telran.mishpahug.entities.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Base64;
import java.util.stream.Collectors;

@Repository
public class ParserExternalLib implements IParser {
    @Override
    public String[] parseToken(String token) {
        String [] arr = token.split(" ");
        return new String(Base64.getDecoder().decode(arr[1].getBytes())).split(":");
    }

    @Override
    public ArrayList<String> getListOfPictures(Profile profile) {
        return (ArrayList<String>) profile.getPictures().stream().
                sorted((a,b)->a.compare(a,b)).
                map(Picture::getUrl).collect(Collectors.toList());
    }

    @Override
    public ArrayList<String> getListOfFoods(Profile profile) {
        return (ArrayList<String>) profile.getFoodsOfProfile().stream().map(FoodProfile::getFood).
                    collect(Collectors.toList());
    }

    @Override
    public ArrayList<String> getListOfLanguages(Profile profile) {
        return (ArrayList<String>) profile.getLanguagesOfProfile().stream().
                    map(Languages::getLanguage).collect(Collectors.toList());
    }
}
