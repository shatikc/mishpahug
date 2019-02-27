package com.telran.mishpahug;

import com.telran.mishpahug.services.daemon.EventCleaner;
import com.telran.mishpahug.services.daemon.FirebaseFCM;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

@SpringBootApplication
public class MishpahugJava2021Application {
    public static void main(String[] args) throws IOException {
        ConfigurableApplicationContext ctx =  SpringApplication.run(MishpahugJava2021Application.class, args);
        ctx.getBean(FirebaseFCM.class).initializeSDK();
        EventCleaner eventCleaner = ctx.getBean(EventCleaner.class);
        eventCleaner.start();
    }
}

