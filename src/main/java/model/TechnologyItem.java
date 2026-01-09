package model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import model.TechnologyCategory;

@Entity
@Table(name = "technology_item")
@Getter
@Setter
public class TechnologyItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String icon;

    @Column(nullable = false)
    private boolean isActive = true;

    @Column(nullable = false)
    private int sortOrder = 0;

    private Long questionCount;

    private Long mcqCount;
    private Long outputBasedCount;
    private Long outputBasedMcqCount;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private TechnologyCategory category;
}
