package com.example.springAssignment_TextService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class TextService {

    @GetMapping("/camelize")
    public String stringCamelCase(@RequestParam String original, @RequestParam(required=false) boolean initialCap){
        String[] newStrings=  original.split("_");
        String camelCaseWord = "";
        if(!initialCap) {
            camelCaseWord = newStrings[0];
        }else {
            camelCaseWord = newStrings[0].substring(0,1).toUpperCase() + newStrings[0].substring(1);
        }

        for (int i = 1; i < newStrings.length; i++) {
            camelCaseWord += (newStrings[i].substring(0,1).toUpperCase() + newStrings[i].substring(1));
        }

        return camelCaseWord;
    }
}
