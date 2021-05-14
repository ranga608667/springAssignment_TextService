package com.example.springAssignment_TextService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
}

