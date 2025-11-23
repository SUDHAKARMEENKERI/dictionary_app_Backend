package controller;

import model.BulkInsertWord;
import model.UserSignUp;
import model.WordMeaning;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.WordService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/words")
@CrossOrigin(origins = "*")
public class WordController {

    private final WordService service;

    public WordController(WordService service) {
        this.service = service;
    }

    @GetMapping
    public List<WordMeaning> getAll() {
        return service.getAllWords();
    }

    @GetMapping("/{id}")
    public WordMeaning getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public ResponseEntity<WordMeaning> create(@RequestBody WordMeaning word) {
        return new ResponseEntity<WordMeaning>(service.create(word), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public WordMeaning update(@PathVariable Long id, @RequestBody WordMeaning word) {
        return service.update(id, word);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{mobile}")
    public List<WordMeaning> findWordByMobile(@PathVariable Long mobile){
        return service.findWordsByMobile(String.valueOf(mobile));
    }

    @PostMapping("/bulkInsert")
    public List<WordMeaning> bulkInsert(@RequestBody BulkInsertWord request) {
        return service.insertMultiple(request.getWords());
    }



}
