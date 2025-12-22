package dao;

import model.QuestionAnswer;
import model.QuestionAnswerResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestionAnswerRepository extends JpaRepository<QuestionAnswer,Long> {

    List<QuestionAnswer> findByMobile(@Param("mobile") String mobile);
}
