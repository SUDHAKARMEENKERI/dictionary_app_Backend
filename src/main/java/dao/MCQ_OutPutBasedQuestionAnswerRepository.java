package dao;

import model.MCQOutPutBasedQuestionAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MCQ_OutPutBasedQuestionAnswerRepository extends JpaRepository <MCQOutPutBasedQuestionAnswer,Long>{
    @Query("""
    SELECT qa
    FROM MCQOutPutBasedQuestionAnswer qa
    WHERE qa.questionType = :questionType
      AND (:topic IS NULL OR qa.topic = :topic)
      AND (:category IS NULL OR qa.category = :category)
    """)
    List<MCQOutPutBasedQuestionAnswer> findMcqQAByParams(@Param("questionType") String questionType,
                                                         @Param("topic") String topic,
                                                         @Param("category") Long category

    );
}
