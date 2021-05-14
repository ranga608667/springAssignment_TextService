package com.example.springAssignment_TextService;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class TextService {

    //Test 1
    @GetMapping("/camelize")
    public String stringCamelCase(@RequestParam String original,@RequestParam (required = false) boolean initialCap){
        String[] newStrings=  original.split("_");
        String camelCaseWord;
        if(!initialCap) {
            camelCaseWord = newStrings[0];
        }else {
            camelCaseWord = newStrings[0].substring(0,1).toUpperCase() +newStrings[0].substring(1);
        }
        for (int i = 1; i < newStrings.length; i++) {
             camelCaseWord += (newStrings[i].substring(0,1).toUpperCase() + newStrings[i].substring(1));
        }

        return camelCaseWord;
    }
    //Test 2
    @GetMapping("/redact")
    public String stringRedact(@RequestParam String original, @RequestParam List<String> badWord){
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
    //Test 3
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

//    @PostMapping("/encode")
//    public String stringEncode(@RequestParam String message,@RequestParam String key){
//        String alphabet="abcdefghijklmnopqrstuvwzyz";
//        String formDataKey=key;
//        String encodedMsg = "";
//        Map<String,String> charPair= new HashMap<>();
//        for(int i=0; i<alphabet.length();i++){
//            int j=i+1;
//            charPair.put(alphabet.substring(i,j),formDataKey.substring(i,j));
//        }
//        for(int k=0; k<=message.length()-1;k++) {
//            int l = k + 1;
//            if (k!=message.length()-1){
//                if (message.substring(k, l).equals(" ")) {
//                    encodedMsg = encodedMsg + " ";
//                } else {
//                    encodedMsg = encodedMsg +  charPair.get(message.substring(k, l));
//                }
//            } else {
//                encodedMsg = encodedMsg + charPair.get(message.substring(k));
//
//            }
//        }
//        return encodedMsg;
//    }
    //Test 4
    @PostMapping("/s/{find}/{replace}")
    public String stringSed(@PathVariable String find,
                          @PathVariable String replace,
                            @RequestParam Map<String,String> formData){
        String ipMessage=formData.get("message").replaceAll(find,replace);
        return ipMessage;
    }
}
