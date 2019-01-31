package com.telran.mishpahug.services.parser;

import com.telran.mishpahug.entities.Profile;

import java.util.ArrayList;

public interface IParser {
    String [] parseToken(String token);

    ArrayList<String> getListOfPictures(Profile profile);

    ArrayList<String> getListOfFoods(Profile profile);

    ArrayList<String> getListOfLanguages(Profile profile);
}
