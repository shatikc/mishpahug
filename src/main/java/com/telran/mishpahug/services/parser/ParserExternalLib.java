package com.telran.mishpahug.services.parser;

import org.springframework.stereotype.Repository;

import java.util.Base64;

@Repository
public class ParserExternalLib implements IParser {
    @Override
    public String[] parseToken(String token) {
        String [] arr = token.split(" ");
        return new String(Base64.getDecoder().decode(arr[1].getBytes())).split(":");
    }
}
