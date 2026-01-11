package model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "programming_question")
@Getter
@Setter
public class ProgrammingQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String title;

    private String difficulty;

    @Column(columnDefinition = "TEXT")
    private String topics; // comma-separated

    @Column(columnDefinition = "TEXT")
    private String prompt;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProgrammingAnswer> answers;

    @Column(columnDefinition = "TEXT")
    private String hints;

    private Boolean isAdmin;
    private String mobile;
}