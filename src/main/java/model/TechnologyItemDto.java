package model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TechnologyItemDto {
    private Long id;
    private String name;
    private String icon;
    private Integer sortOrder;
    private Long questionCount;
    private Long mcqCount;
    private Long outputBasedCount;
    private Long outputBasedMcqCount;

    public TechnologyItemDto(Long id, String name, String icon,
                             Integer sortOrder, Long questionCount,
                            Long mcqCount, Long outputBasedCount,
                             Long outputBasedMcqCount){
        this.id = id;
        this.name = name;
        this.icon = icon;
        this.sortOrder = sortOrder;
        this.questionCount = questionCount;
        this.mcqCount = mcqCount;
        this.outputBasedCount = outputBasedCount;
        this.outputBasedMcqCount = outputBasedMcqCount;

    }

}

