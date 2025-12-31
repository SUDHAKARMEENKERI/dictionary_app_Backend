package controller;

import jakarta.validation.Valid;
import model.MCQOutPutBasedQuestionAnswer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.MCQQuestionAnswerService;

import java.util.List;

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

//    @GetMapping("/getMcq")
//    public List<MCQOutPutBasedQuestionAnswer> get() {
//        return this.service.getAll();
//    }

    @GetMapping("/getMcq")
    public List<MCQOutPutBasedQuestionAnswer> get( @RequestParam String questionType,
                                                   @RequestParam(required = false) String topic,
                                                   @RequestParam(required = false) Long category) {
        return this.service.getMcqQAByParams(questionType, topic, category);
    }
}
