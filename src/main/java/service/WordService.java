package service;

import dao.WordRepository;
import errorHandle.DuplicateWordException;
import errorHandle.ResourceNotFoundException;
import model.WordMeaning;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WordService {

    private final WordRepository repo;

    public WordService(WordRepository repo) {
        this.repo = repo;
    }

    public List<WordMeaning> getAllWords() {
        return repo.findAll();
    }

    public WordMeaning getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Word not found with ID " + id));
    }

    public WordMeaning create(WordMeaning wordMeaning) {
        if (repo.existsByWord(wordMeaning.getWord())) {
            throw new DuplicateWordException("Word already exists: " + wordMeaning.getWord());
        } else {
            return repo.save(wordMeaning);
        }
    }

    public WordMeaning update(Long id, WordMeaning updatedWord) {
        WordMeaning existing = getById(id);
        existing.setWord(updatedWord.getWord());
        existing.setMeaning(updatedWord.getMeaning());
        return repo.save(existing);
    }

    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new ResourceNotFoundException("Word not found with ID " + id);
        }
        repo.deleteById(id);
    }
    public List<WordMeaning> findWordsByMobile(String mobile){
        return repo.findWordsByMobile(mobile);
    }
}
