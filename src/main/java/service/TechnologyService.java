package service;

import dao.TechnologyCategoryRepository;
import dao.TechnologyItemRepository;
import model.*;
import org.springframework.beans.factory.annotation.Autowired;
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
        Map<String, QuestionTypeCount> typeCounts = getQuestionTypeCountsByTopic();
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
            String itemName = ((String) r[5]).toLowerCase();
            QuestionTypeCount qtc = typeCounts.getOrDefault(itemName, new QuestionTypeCount(0, 0,0));
            category.getItems().add(
                    new TechnologyItemDto(
                            (Long) r[4],
                            (String) r[5],
                            (String) r[6],
                            (Integer) r[7],
                            (Long) r[8],
                            qtc.getMcqCount(),
                            qtc.getOutputBasedCount(),
                            qtc.getOutputBasedMcqCount()
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

    private Map<String, QuestionTypeCount> getQuestionTypeCountsByTopic() {
        List<Object[]> rows = itemRepo.findQuestionTypeCountsByTopic();
        Map<String, QuestionTypeCount> map = new HashMap<>();
        for (Object[] r : rows) {
            String topic = (String) r[0];
            String type = (String) r[1];
            Long count = (Long) r[2];
            QuestionTypeCount qtc = map.getOrDefault(topic, new QuestionTypeCount(0, 0, 0));
            if ("MCQ".equals(type)) {
                qtc = new QuestionTypeCount(count, qtc.getOutputBasedCount(), qtc.getOutputBasedMcqCount());
            } else if ("OUTPUTBASED".equals(type)) {
                qtc = new QuestionTypeCount(qtc.getMcqCount(), count, qtc.getOutputBasedMcqCount());
            } else if("OUTPUTBASEDMCQ".equals(type)){
                qtc = new QuestionTypeCount(qtc.getMcqCount(), qtc.getOutputBasedCount(), count);
            }
            map.put(topic, qtc);
        }
        return map;
    }
}
