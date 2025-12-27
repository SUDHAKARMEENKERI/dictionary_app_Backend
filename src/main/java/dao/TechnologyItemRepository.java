package dao;

import model.DropdownResponse;
import model.TechnologyItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TechnologyItemRepository
        extends JpaRepository<TechnologyItem, Long> {

    @Query("""
        SELECT new model.DropdownResponse(i.id, i.name)
        FROM TechnologyItem i
        WHERE i.isActive = true
          AND i.category.id = :category_id
        ORDER BY i.sortOrder
    """)
    List<DropdownResponse> findItemsByCategory(@Param("category_id") Long category_id);
}
