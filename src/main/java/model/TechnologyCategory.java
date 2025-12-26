package model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "technology_category")
@Getter
@Setter
public class TechnologyCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String slug;

    @Column(nullable = false)
    private boolean isActive = true;

    @Column(nullable = false)
    private int sortOrder = 0;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<TechnologyItem> items;
}
