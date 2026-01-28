package controller;

import jakarta.validation.Valid;
import model.MCQOutPutBasedQuestionAnswer;
import model.WordMeaning;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import service.MCQQuestionAnswerService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/mcqQuestions")
@CrossOrigin(origins = "*")
public class MCQOutPutBasedQuestionAnswerController {

    private final MCQQuestionAnswerService service;

    public MCQOutPutBasedQuestionAnswerController(MCQQuestionAnswerService service) {
        this.service = service;
    }

    @PostMapping("/mcq")
    public ResponseEntity<MCQOutPutBasedQuestionAnswer> create(
            @Valid @RequestBody MCQOutPutBasedQuestionAnswer request) {

        return ResponseEntity.ok(service.save(request));
    }

    @GetMapping("/getMcq")
    public List<MCQOutPutBasedQuestionAnswer> get( @RequestParam String questionType,
                                                   @RequestParam(required = false) String topic,
                                                   @RequestParam(required = false) Long category) {
        return this.service.getMcqQAByParams(questionType, topic, category);
    }

    @PutMapping("update/{id}")
    public MCQOutPutBasedQuestionAnswer update(@PathVariable Long id, @RequestBody MCQOutPutBasedQuestionAnswer mcqQA) {
        return (MCQOutPutBasedQuestionAnswer) service.updateMCQQA(id, mcqQA);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteMCQ(id);
        return ResponseEntity.noContent().build();
    }

    // src/main/java/controller/MCQOutPutBasedQuestionAnswerController.java
    @PostMapping("/bulkUpload")
    public ResponseEntity<Map<String, Object>> bulkUpload(@RequestParam("file") MultipartFile file) {
        Map<String, Object> response = new HashMap<>();
        try {
            List<MCQOutPutBasedQuestionAnswer> saved = service.bulkUploadFromExcel(file);
            response.put("result", "Bulk upload successful");
            response.put("recordsAdded", saved.size());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("result", "Bulk upload failed");
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

}
