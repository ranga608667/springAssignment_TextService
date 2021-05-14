package com.example.springAssignment_TextService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class TextService {

    @GetMapping("/camelize")
    public String stringCamelCase(@RequestParam String original){
        String[] newStrings=  original.split("_");
        String camelCaseWord = newStrings[0];
        for (int i = 1; i < newStrings.length; i++) {
             camelCaseWord += (newStrings[i].substring(0,1).toUpperCase() + newStrings[i].substring(1));
        }

        return camelCaseWord;
    }
}
