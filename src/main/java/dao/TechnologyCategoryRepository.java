package dao;

import model.DropdownResponse;
import model.TechnologyCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TechnologyCategoryRepository
        extends JpaRepository<TechnologyCategory, Long> {
    @Query("""
    SELECT
        c.id,
        c.name,
        c.slug,
        c.sortOrder,
        i.id,
        i.name,
        i.icon,
        i.sortOrder,
        COUNT(qa.id)
    FROM TechnologyCategory c
    JOIN c.items i
    LEFT JOIN QuestionAnswer qa
        WITH LOWER(qa.topic) = LOWER(i.name)
    WHERE c.isActive = true
      AND i.isActive = true
    GROUP BY
        c.id, c.name, c.slug, c.sortOrder,
        i.id, i.name, i.icon, i.sortOrder
    ORDER BY c.sortOrder, i.sortOrder
    """)
    List<Object[]> findCategoryItemWithQuestionCount();

    @Query("""
        SELECT new model.DropdownResponse(c.id, c.name)
        FROM TechnologyCategory c
        WHERE c.isActive = true
        ORDER BY c.sortOrder
    """)
    List<DropdownResponse> findActiveCategories();

}
