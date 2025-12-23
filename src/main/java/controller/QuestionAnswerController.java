package controller;

import model.QuestionAnswer;
import model.QuestionAnswerResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import service.QuestionAnswerService;

import java.util.Base64;
import java.util.List;
import java.util.Map;

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
            @RequestParam String mobile,
            @RequestParam(required = false) MultipartFile image) {
        try {
            QuestionAnswer saved = service.saveQA(question, answer, topic,mobile, image);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<QuestionAnswer> updateQA(
            @RequestParam Long id,
            @RequestParam String question,
            @RequestParam String answer,
            @RequestParam String topic,
            @RequestParam String mobile,
            @RequestParam(required = false) MultipartFile image) {
        try {
            QuestionAnswer saved = service.updateQA(id, question, answer, topic, mobile,image);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionAnswerResponse> getQuestionAnswer(@PathVariable Long id) {

        QuestionAnswer qa = service.getById(id);

        QuestionAnswerResponse response = new QuestionAnswerResponse();
        response.setId(qa.getId());
        response.setQuestion(qa.getQuestion());
        response.setAnswer(qa.getAnswer());
        response.setTopic(qa.getTopic());

        if (qa.getImage() != null) {
            response.setImageBase64(
                    Base64.getEncoder().encodeToString(qa.getImage())
            );
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping("/getAllUserQA")
    public List<QuestionAnswer> getAllQuestionAnswer() {
        return service.getAll();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/totalQuestionAnswerCount")
    public Map<String, Long> getTotalRecords() {
        long count = service.getTotalRecords();
        return Map.of("totalQuestionAnswerCount", count);
    }

    @GetMapping("/user/count/{mobile}")
    public int findQACountByMobile(@PathVariable Long mobile){
        return service.getByMobile(String.valueOf(mobile)).size();
    }
}
