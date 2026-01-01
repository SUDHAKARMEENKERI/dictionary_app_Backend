package controller;
import model.DropdownResponse;
import model.QuestionAnswer;
import model.QuestionAnswerResponse;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
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
    private static final String UPLOAD_DIR = "uploads";
    public QuestionAnswerController(QuestionAnswerService service) {
        this.service = service;
    }

    @PostMapping(value = "/create", consumes = {"multipart/form-data"})
    public ResponseEntity<QuestionAnswer> createQA(
            @RequestParam String question,
            @RequestParam String answer,
            @RequestParam String topic,
            @RequestParam String mobile,
            @RequestParam String level,
            @RequestParam String category,
            @RequestParam String questionType,
            @RequestParam(required = false) MultipartFile image) {
        try {
            QuestionAnswer saved = service.saveQA(question, answer, topic,mobile,level,category, questionType, image);
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
            @RequestParam String level,
            @RequestParam String category,
            @RequestParam String questionType,
            @RequestParam(required = false) MultipartFile image) {
        try {
            QuestionAnswer saved = service .updateQA(id, question, answer, topic,
                    mobile,level, category, questionType,image);
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
        response.setLevel(qa.getLevel());
        response.setCategory(qa.getCategory());
        response.setQuestionType(qa.getQuestionType());


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
    @PostMapping(
            value = "/bulkUploadQA",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public Map<String, String> upload(

            @RequestParam("excel") MultipartFile excel) {

        service.bulkUpload(excel);
        return Map.of("Result","Bulk upload successful");
    }

    @GetMapping("/list")
    public Page<QuestionAnswer> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        return service.getPaginated(page, size);
    }

    @GetMapping
    public List<?> getQuestionsByTopic(
            @RequestParam String topic) {
        return service.getByTopic(topic);
    }

}
