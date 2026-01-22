package controller;

import jakarta.validation.Valid;
import model.MCQOutPutBasedQuestionAnswer;
import model.WordMeaning;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import service.MCQQuestionAnswerService;

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

    @PostMapping("/bulk-upload")
    public ResponseEntity<Map<String, String>> bulkUpload(@RequestParam("file") MultipartFile file) {
        try {
            int count = service.bulkUploadFromExcel(file);
            return ResponseEntity.ok(Map.of("result", "Bulk upload successful", "recordsAdded", String.valueOf(count)));
        } catch (Exception e) {
            // Log the error for debugging
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Bulk upload failed", "details", e.getMessage()));
        }
    }

}
