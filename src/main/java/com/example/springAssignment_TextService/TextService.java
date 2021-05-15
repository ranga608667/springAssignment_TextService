package com.example.springAssignment_TextService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

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


    @GetMapping("/redact")
    public String stringRedact(@RequestParam String original, @RequestParam List<String> badWord) {
        List<Integer>wordLength=new ArrayList<>();
        List<String> newWord=new ArrayList<>();
        String redactedString = original;
        for (int i=0;i<badWord.size();i++) {
            wordLength.add(badWord.get(i).length());
        }
        for (int j=0;j<wordLength.size();j++) {
            String aRep="";
            for(int k=1;k<=wordLength.get(j);k++){
                aRep=aRep + "*";
            }
            newWord.add(aRep);
        }
        for (int l=0;l<badWord.size();l++) {
            redactedString = redactedString.replaceAll(badWord.get(l), newWord.get(l));
        }
        return redactedString;
    }

    @PostMapping("/encode")
    public String encode(@RequestParam Map<String, String> formData) {
        String key = formData.get("key");
        String message = formData.get("message");
        Map<Character, Character> alphabet = new HashMap<>();
        String oldAlphabet = "abcdefghijklmnopqrstuvwzyz";
        for(int i = 0; i < oldAlphabet.length(); i++) {
            alphabet.put(oldAlphabet.charAt(i), key.charAt(i));
        }
        String encodedMessage = "";
        for(int i = 0; i < message.length(); i++) {
            if (message.charAt(i) == ' ') {
                encodedMessage += ' ';
            }else {
                char encode = alphabet.get(message.charAt(i));
                encodedMessage += encode;
            }
        }
        return encodedMessage;
    }
    @PostMapping("/s/little/lot")
    public String sed(@RequestParam Map<String, String> formData){
        String message = formData.get("message");
        String find =formData.get("find");
        String replace =formData.get("replace");

        return message.replaceAll(find, replace);
    }
}
