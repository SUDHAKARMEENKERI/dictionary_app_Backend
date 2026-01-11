package controller;

import model.ProgrammingAnswer;
import model.ProgrammingQuestion;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.ProgrammingQuestionService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/programming-questions")
@CrossOrigin("*")
public class ProgrammingQuestionController {
    private final ProgrammingQuestionService service;

    public ProgrammingQuestionController(ProgrammingQuestionService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ProgrammingQuestionDto> create(@RequestBody ProgrammingQuestionDto dto) {
        ProgrammingQuestion pq = dto.toEntity();
        ProgrammingQuestion saved = service.create(pq);
        return ResponseEntity.ok(ProgrammingQuestionDto.fromEntity(saved));
    }

    @GetMapping
    public List<ProgrammingQuestionDto> getAll() {
        return service.getAll().stream()
                .map(ProgrammingQuestionDto::fromEntity)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProgrammingQuestionDto> getById(@PathVariable Long id) {
        Optional<ProgrammingQuestion> pq = service.getById(id);
        return pq.map(q -> ResponseEntity.ok(ProgrammingQuestionDto.fromEntity(q)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProgrammingQuestionDto> update(@PathVariable Long id, @RequestBody ProgrammingQuestionDto dto) {
        ProgrammingQuestion pq = dto.toEntity();
        ProgrammingQuestion updated = service.update(id, pq);
        return ResponseEntity.ok(ProgrammingQuestionDto.fromEntity(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    // DTOs
    public static class ProgrammingQuestionDto {
        public Long id;
        public String title;
        public String difficulty;
        public List<String> topics;
        public String prompt;
        public String hints;
        public List<ProgrammingAnswerDto> answers;
        public Boolean isAdmin;
        public String mobile;

        public static ProgrammingQuestionDto fromEntity(ProgrammingQuestion pq) {
            ProgrammingQuestionDto dto = new ProgrammingQuestionDto();
            dto.id = pq.getId();
            dto.title = pq.getTitle();
            dto.difficulty = pq.getDifficulty();
            dto.topics = pq.getTopics() != null && !pq.getTopics().isEmpty()
                    ? Arrays.asList(pq.getTopics().split(","))
                    : List.of();
            dto.prompt = pq.getPrompt();
            dto.hints = pq.getHints();
            dto.answers = pq.getAnswers() != null
                    ? pq.getAnswers().stream().map(ProgrammingAnswerDto::fromEntity).collect(Collectors.toList())
                    : List.of();
            dto.isAdmin = pq.getIsAdmin();
            dto.mobile = pq.getMobile();
            return dto;
        }

        public ProgrammingQuestion toEntity() {
            ProgrammingQuestion pq = new ProgrammingQuestion();
            pq.setId(this.id);
            pq.setTitle(this.title);
            pq.setDifficulty(this.difficulty);
            pq.setTopics(this.topics != null ? String.join(",", this.topics) : "");
            pq.setPrompt(this.prompt);
            pq.setHints(this.hints);
            pq.setIsAdmin(this.isAdmin);
            pq.setMobile(this.mobile);
            if (this.answers != null) {
                List<ProgrammingAnswer> answerEntities = this.answers.stream()
                        .map(a -> {
                            ProgrammingAnswer ans = a.toEntity();
                            ans.setQuestion(pq);
                            return ans;
                        })
                        .collect(Collectors.toList());
                pq.setAnswers(answerEntities);
            }
            return pq;
        }
    }

    public static class ProgrammingAnswerDto {
        public Long id;
        public String technology;
        public String answer;

        public static ProgrammingAnswerDto fromEntity(ProgrammingAnswer pa) {
            ProgrammingAnswerDto dto = new ProgrammingAnswerDto();
            dto.id = pa.getId();
            dto.technology = pa.getTechnology();
            dto.answer = pa.getAnswer();
            return dto;
        }

        public ProgrammingAnswer toEntity() {
            ProgrammingAnswer pa = new ProgrammingAnswer();
            pa.setId(this.id);
            pa.setTechnology(this.technology);
            pa.setAnswer(this.answer);
            return pa;
        }
    }
}