package com.telran.mishpahug;

import com.telran.mishpahug.repository.IEventCleanerRepository;
import com.telran.mishpahug.services.EventCleaner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class MishpahugJava2021Application {
    public static void main(String[] args) {
        ConfigurableApplicationContext ctx =  SpringApplication.run(MishpahugJava2021Application.class, args);
        EventCleaner eventCleaner = ctx.getBean(EventCleaner.class);
        eventCleaner.start();
    }
}

