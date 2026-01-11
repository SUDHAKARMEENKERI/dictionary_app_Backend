package service;

import dao.ProgrammingQuestionRepository;
import model.ProgrammingQuestion;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProgrammingQuestionService {
    private final ProgrammingQuestionRepository repository;

    public ProgrammingQuestionService(ProgrammingQuestionRepository repository) {
        this.repository = repository;
    }

    public ProgrammingQuestion create(ProgrammingQuestion pq) {
        return repository.save(pq);
    }

    public List<ProgrammingQuestion> getAll() {
        return repository.findAll();
    }

    public Optional<ProgrammingQuestion> getById(Long id) {
        return repository.findById(id);
    }

    public ProgrammingQuestion update(Long id, ProgrammingQuestion pq) {
        pq.setId(id);
        return repository.save(pq);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}