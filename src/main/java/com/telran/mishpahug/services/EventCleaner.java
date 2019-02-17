package com.telran.mishpahug.services;


import com.telran.mishpahug.repository.IEventCleanerRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class EventCleaner implements Runnable {

    @Autowired
    IEventCleanerRepository cleanerRepo;

    @Override
    public void run() {

    }
}
