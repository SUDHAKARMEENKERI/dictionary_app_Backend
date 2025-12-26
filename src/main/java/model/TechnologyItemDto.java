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

    public TechnologyItemDto(Long id, String name, String icon,
                             Integer sortOrder, Long questionCount) {
        this.id = id;
        this.name = name;
        this.icon = icon;
        this.sortOrder = sortOrder;
        this.questionCount = questionCount;
    }
}

