package dev.Blogapplication.utils;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class IdGenerator {
      
    public String generatedId(String name){

        Random random = new Random();
        String id = name + random.nextInt(1000000000);
        return id;
    }
}
