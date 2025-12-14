package dao;

import model.QuestionAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionAnswerRepository extends JpaRepository<QuestionAnswer,Long> {

}
