package dao;

import model.ProgrammingQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgrammingQuestionRepository extends JpaRepository<ProgrammingQuestion, Long> {
}