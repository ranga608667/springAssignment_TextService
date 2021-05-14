package com.example.springAssignment_TextService;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sample {
    public static void main(String[] args) {
        List<String> badWord= new ArrayList<>();
        badWord.add("little");
        badWord.add("this");

        String original="A little of this and a little of that";

        List<Integer>wordLength=new ArrayList<>();
        List<String> newWord=new ArrayList<>();
        String redactedString = "";
        Map<String,String> formData=new HashMap<>();
        formData.put("message","a little of this and a little of that");
        formData.put("key", "mcxrstunopqabyzdefghijklvw");
        String alphabet="abcdefghijklmnopqrstuvwzyz";
        String formKey=formData.get("key");
        String encodedMsg= "";
        String message= formData.get("message");
        System.out.println(formKey);
        System.out.println(formKey.length());
        Map<String,String> charPair= new HashMap<>();

        for(int i=0; i<alphabet.length();i++){
           int j=i+1;
            charPair.put(alphabet.substring(i,j),formKey.substring(i,j));
        }

        System.out.println(charPair);

        for(int k=0; k<=message.length()-1;k++) {
            int l = k + 1;
            if (k!=message.length()-1){
                if (message.substring(k, l).equals(" ")) {
                    encodedMsg = encodedMsg + " ";
                } else {
                    encodedMsg = encodedMsg +  charPair.get(message.substring(k, l));
                }
            } else {
                encodedMsg = encodedMsg + charPair.get(message.substring(k));

            }
        }
        System.out.println(message);
        System.out.println(encodedMsg);
        for (int i=0;i<badWord.size();i++) {
            wordLength.add(badWord.get(i).length());
            System.out.println(wordLength.get(i));
        }

        for (int j=0;j<wordLength.size();j++) {
            String aRep="";
            for(int k=1;k<=wordLength.get(j);k++){
                aRep=aRep + "*";
            }
            newWord.add(aRep);
            System.out.println(newWord.get(j));
        }
        System.out.println("Sample");
    }
}
