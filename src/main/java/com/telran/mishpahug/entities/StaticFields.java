package com.telran.mishpahug.entities;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@Getter

public  class StaticFields implements Serializable {
    private static final long serialVersionUID = 1L;
    private  String [] confession = {"religious", "irreligious"};
    private  String [] gender = {"male", "female"};
    private  String [] maritalStatus = {"married", "single", "couple"};
    private  String [] foodPreferences = {"vegetarian", "kosher", "non-vegetarian"};
    private  String [] languages = {"Hebrew", "English", "Russian"};
    private  String [] holiday = {"Pesah", "Shabbat", "Other"};

}
