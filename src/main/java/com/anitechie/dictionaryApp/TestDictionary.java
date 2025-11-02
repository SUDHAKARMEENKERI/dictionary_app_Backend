package com.anitechie.dictionaryApp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestDictionary {

    @GetMapping("abcd")
    public String sayHello(){
        return "Hello";
    }
}
