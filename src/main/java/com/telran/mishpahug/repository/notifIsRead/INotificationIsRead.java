package com.telran.mishpahug.repository.notifIsRead;

import com.telran.mishpahug.entities.Notification;
import com.telran.mishpahug.entities.Profile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface INotificationIsRead extends CrudRepository<Notification,Object> {

    @Query("SELECT p FROM Notification p where p.notificationId = :notId and p.ownerEmail = :owner")
    Notification getEntity(long notId, Profile owner);
}