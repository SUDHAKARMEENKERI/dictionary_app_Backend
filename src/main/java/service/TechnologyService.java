package service;

import dao.TechnologyCategoryRepository;
import model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TechnologyService {

    @Autowired
    private TechnologyCategoryRepository repository;

//    public List<TechnologyResponse> getVisibleTechnologies() {
//
//        return repository.findVisibleTechnologies()
//                .stream()
//                .sorted(Comparator.comparingInt(TechnologyCategory::getSortOrder))
//                .map(cat -> new TechnologyResponse(
//                        cat.getName(),
//                        cat.getSlug(),
//                        cat.getItems().stream()
//                                .filter(TechnologyItem::isActive)
//                                .sorted(Comparator.comparingInt(TechnologyItem::getSortOrder))
//                                .map(i -> new TechnologyItemResponse(i.getName(), i.getIcon()))
//                                .toList()
//                ))
//                .toList();
//    }

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

}
