package com.telran.mishpahug.api;

public interface URLConstants {
    //Post requests:
    String eventListInProgress = "/event/allprogresslist";
    String registrationUser = "/user/registration";
    String loginUser = "/user/login";
    String addEvent = "/event/creation";
    String updateUserProfile = "/user/profile";
    String addFirebaseToken = "/user/firebasetoken/add";

    //Delete requests:
    String deleteFirebaseToken = "/user/firebasetoken/delete";

    //Get requests:
    String getUser = "/user/profile";

}
