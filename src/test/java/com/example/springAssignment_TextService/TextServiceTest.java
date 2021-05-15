package com.example.springAssignment_TextService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TextService.class)
public class TextServiceTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void stringCamelize() throws Exception{
        RequestBuilder request=get("/camelize?original=this_is_a_thing");
        this.mvc.perform(request).andExpect(status().isOk())
                .andExpect(content().string("thisIsAThing"));

    }

    @Test
    public void stringCamelizeWithInitialCap() throws Exception{
        RequestBuilder request=get("/camelize?original=this_is_a_thing&initialCap=true");
        this.mvc.perform(request).andExpect(status().isOk())
                .andExpect(content().string("ThisIsAThing"));

    }

    @Test
    public void stringRedact() throws Exception{
        RequestBuilder request=get("/redact?original=A little of this and a little of that&badWord=little&badWord=this");
        this.mvc.perform(request).andExpect(status().isOk())
               .andExpect(content().string("A ****** of **** and a ****** of that"));

    }

    @Test
    public void stringRedactOneWord() throws Exception{
        RequestBuilder request=get("/redact?original=A little of this and a little of that&badWord=little");
        this.mvc.perform(request).andExpect(status().isOk())
                .andExpect(content().string("A ****** of this and a ****** of that"));

    }

    @Test
    public void testEncode() throws Exception{
        RequestBuilder request = post("/encode")
                .param("message","a little of this and a little of that")
                .param("key", "mcxrstunopqabyzdefghijklvw");

        this.mvc.perform(request).andExpect(status().isOk())
                .andExpect(content().string("m aohhas zt hnog myr m aohhas zt hnmh"));
    }

    @Test
    public void testSed() throws Exception{
        RequestBuilder request = post("/s/little/lot")
                .param("find","little")
                .param("replace","lot")
                .param("message", "a little of this and a little of that");
        this.mvc.perform(request).andExpect(status().isOk())
                .andExpect(content().string("a lot of this and a lot of that"));


    }

}

