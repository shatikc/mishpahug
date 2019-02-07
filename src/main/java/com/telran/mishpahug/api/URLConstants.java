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
    String staticFields = "/user/staticfields"; // unauthorized request
    String getUser = "/user/profile";
    String getCountUnreadNotifications = "/notification/count";
    String getListNotifications = "/notification/list";
    String getMyEventInfo = "/event/own/{eventId}";
    String getListParticipations = "/event/participationlist";

    //Put requests:
    String notificationIsRead = "/notification/isRead/{notificationId}";
    String voteForEvent = "/event/vote/{eventId}/{voteCount}";
    String changeStatusEvent = "/event/pending/{eventId}";
    String subscribeToEvent = "/event/subscription/{eventId}";
    String unsubscribeFromEvent = "/event/unsubscription/{eventId}";
}
