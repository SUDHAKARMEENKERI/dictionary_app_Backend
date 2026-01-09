package controller;

import model.QuizResult;
import service.QuizResultService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/quiz-attempts")
@CrossOrigin(origins = "*")
public class QuizResultController {
    private final QuizResultService quizResultService;

    public QuizResultController(QuizResultService quizResultService) {
        this.quizResultService = quizResultService;
    }

    @PostMapping
    public QuizResult saveResult(@RequestBody QuizResult result) {
        return quizResultService.saveResult(result);
    }

    @GetMapping
    public List<QuizResult> getShowResults() {
        return quizResultService.getShowResults();
    }

    @PutMapping("/hide-all")
    public int hideAllShowResults() {
        return quizResultService.hideAllShowResults();
    }
}
