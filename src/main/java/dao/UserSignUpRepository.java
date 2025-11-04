package dao;

import model.LoginUser;
import model.UserSignUp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserSignUpRepository extends JpaRepository<UserSignUp, Long> {
    boolean existsByMobile(String mobile);
    @Query("SELECT u FROM UserSignUp u WHERE u.mobile = :mobile")
    Optional<UserSignUp> findByMobile(@Param("mobile") String mobile);


}
