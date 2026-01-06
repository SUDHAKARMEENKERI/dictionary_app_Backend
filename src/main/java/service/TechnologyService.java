package service;

import dao.TechnologyCategoryRepository;
import dao.TechnologyItemRepository;
import model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TechnologyService {

    @Autowired
    private TechnologyCategoryRepository repository;
    @Autowired
    private TechnologyItemRepository itemRepo;

    public List<TechnologyCategoryDto> getTechnologyForUi() {

        List<Object[]> rows = repository.findCategoryItemWithQuestionCount();

        Map<Long, TechnologyCategoryDto> map = new LinkedHashMap<>();

        for (Object[] r : rows) {

            Long categoryId = (Long) r[0];

            TechnologyCategoryDto category =
                    map.computeIfAbsent(categoryId, id ->
                            new TechnologyCategoryDto(
                                    (Long) r[0],
                                    (String) r[1],
                                    (String) r[2],
                                    (Integer) r[3]
                            )
                    );

            category.getItems().add(
                    new TechnologyItemDto(
                            (Long) r[4],
                            (String) r[5],
                            (String) r[6],
                            (Integer) r[7],
                            (Long) r[8]
                    )
            );
        }

        return new ArrayList<>(map.values());
    }

    public List<DropdownResponse> getCategories() {
        return repository.findActiveCategories();
    }


    public List<DropdownResponse> getItems(Long category_id) {
        return itemRepo.findItemsByCategory(category_id);
    }

}
