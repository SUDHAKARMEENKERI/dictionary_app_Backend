package model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "mcq_based_question_answer")
@Getter
@Setter
public class MCQOutPutBasedQuestionAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String question;
    private String answer;
    private String category;
    private String topic;
    private String mobile;
    private String level;
    private String questionType;
    @Column(columnDefinition = "TEXT")
    private String code;
    private boolean isAdmin;

    @ElementCollection
    private List<String> options;
    private String correctAnswer;


}
