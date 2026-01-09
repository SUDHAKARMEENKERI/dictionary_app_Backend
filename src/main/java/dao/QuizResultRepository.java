package dao;

import model.QuizResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface QuizResultRepository extends JpaRepository<QuizResult, Long> {
    List<QuizResult> findByShowResultTrue();

    @Modifying
    @Transactional
    @Query("UPDATE QuizResult q SET q.showResult = false")
    int hideAllShowResults();
}
