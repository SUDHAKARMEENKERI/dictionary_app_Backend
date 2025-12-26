package model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class TechnologyCategoryDto {
    private Long id;
    private String name;
    private String slug;
    private Integer sortOrder;
    private List<TechnologyItemDto> items = new ArrayList<>();

    public TechnologyCategoryDto(Long id, String name, String slug, Integer sortOrder) {
        this.id = id;
        this.name = name;
        this.slug = slug;
        this.sortOrder = sortOrder;
    }
}
