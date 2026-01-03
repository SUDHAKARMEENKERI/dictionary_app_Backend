package dao;

import model.QuestionAnswer;
import Helper.QuestionAnswerResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface QuestionAnswerRepository extends JpaRepository<QuestionAnswer,Long> {
    Optional<QuestionAnswer> findByQuestion(String word);
    Page<QuestionAnswer> findAll(Pageable pageable);
    List<QuestionAnswer> findByMobile(@Param("mobile") String mobile);

    @Query("""
    SELECT qa
    FROM QuestionAnswer qa
    WHERE LOWER(TRIM(qa.topic)) = LOWER(TRIM(:topic))
    """)
    List<QuestionAnswer> findQAByTopic(@Param("topic") String topic);
}
