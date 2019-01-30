package com.telran.mishpahug.repository.notifIsRead;

import com.telran.mishpahug.entities.Notification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface INotificationIsRead extends CrudRepository<Notification,Object> {

    @Query("SELECT p FROM Notification p where p.notificationId = :notId")
    Notification getEntitie(long notId);
}