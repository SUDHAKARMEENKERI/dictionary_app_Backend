package service;

import dao.WordRepository;
import errorHandle.DuplicateWordException;
import errorHandle.ResourceNotFoundException;
import model.BulkInsertWord;
import model.WordMeaning;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
            return repo.save(wordMeaning);
    }

    public WordMeaning update(Long id, WordMeaning updatedWord) {
        WordMeaning existing = getById(id);
        existing.setWord(updatedWord.getWord().toString());
        existing.setMeaning(updatedWord.getMeaning());
        existing.setFirstName(updatedWord.getFirstName());
        existing.setLastName(updatedWord.getLastName());
        existing.setShow_for_others(updatedWord.getShow_for_others());
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

    @Transactional
    public List<WordMeaning> insertMultiple(List<BulkInsertWord.WordMeaningDto> wordList) {
        List<WordMeaning> saved = new ArrayList<>();
        for (BulkInsertWord.WordMeaningDto dto : wordList) {
            // Check if word already exists
            if (repo.findByWord(dto.getWord()).isEmpty()) {
                WordMeaning wm = new WordMeaning();
                wm.setFirstName(dto.getFirstName());
                wm.setLastName(dto.getLastName());
                wm.setMeaning(dto.getMeaning());
                wm.setMobile(dto.getMobile());
                wm.setShow_for_others(dto.getShow_for_others());
                wm.setWord(dto.getWord());
                saved.add(repo.save(wm));
            }
            // else skip or update depending on your business logic
        }
        return saved;
    }

    public long getTotalRecords() {
        return repo.count();
    }

}
