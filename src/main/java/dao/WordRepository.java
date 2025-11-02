package dao;

import model.WordMeaning;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WordRepository extends JpaRepository<WordMeaning, Long> {
    Optional<WordMeaning> findByWord(String word);
}
