package dao;

import model.UserSignUp;
import model.WordMeaning;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface WordRepository extends JpaRepository<WordMeaning, Long> {
    Optional<WordMeaning> findByWord(String word);
    boolean existsByWord(String word);

    @Query("SELECT u FROM WordMeaning u WHERE u.mobile = :mobile")
    List<WordMeaning> findWordsByMobile(@Param("mobile") String mobile);

}
