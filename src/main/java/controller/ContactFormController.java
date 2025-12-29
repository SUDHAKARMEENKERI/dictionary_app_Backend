package controller;

import jakarta.validation.Valid;
import model.ContactForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.ContactFormService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/contact")
@CrossOrigin(origins = "*")
public class ContactFormController {

    @Autowired
    private ContactFormService service;

    @PostMapping
    public ResponseEntity<Map<String, String>> submitContact(@Valid @RequestBody ContactForm request) {
        Map<String, String> response = new HashMap<>();

        service.save(request);
        response.put("Status","Contact Details Successfully");
        return ResponseEntity.ok(response);
    }
}
