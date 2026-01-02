package dao;

import model.PageView;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.Optional;

public interface PageViewRepository extends JpaRepository<PageView, Long> {
    Optional<PageView> findByPageNameAndDate(String pageName, LocalDate date);
}