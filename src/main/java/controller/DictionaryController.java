package controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DictionaryController {

    @GetMapping("/abc")
    public String getListOfWords(){
        return "Hello";
    }
}
