package service;

import dao.QuizResultRepository;
import model.QuizResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class QuizResultService {
    private final QuizResultRepository repo;

    public QuizResultService(QuizResultRepository repo) {
        this.repo = repo;
    }

    public QuizResult saveResult(QuizResult result) {
        return repo.save(result);
    }

    public List<QuizResult> getAllResults() {
        return repo.findAll();
    }

    public List<QuizResult> getShowResults() {
        return repo.findByShowResultTrue();
    }

    @Transactional
    public int hideAllShowResults() {
        return repo.hideAllShowResults();
    }
}
