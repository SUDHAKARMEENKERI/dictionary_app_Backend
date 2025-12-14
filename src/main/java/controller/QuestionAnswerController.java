package controller;

import model.QuestionAnswer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import service.QuestionAnswerService;

@RestController
@RequestMapping("/api/qa")
@CrossOrigin("*")
public class QuestionAnswerController {

    private final QuestionAnswerService service;

    public QuestionAnswerController(QuestionAnswerService service) {
        this.service = service;
    }

    @PostMapping(value = "/create", consumes = {"multipart/form-data"})
    public ResponseEntity<QuestionAnswer> createQA(
            @RequestParam String question,
            @RequestParam String answer,
            @RequestParam String topic,
            @RequestParam(required = false) MultipartFile image) {
        try {
            QuestionAnswer saved = service.saveQA(question, answer, topic, image);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
