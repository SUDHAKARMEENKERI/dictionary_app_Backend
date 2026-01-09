package model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "quiz_result")
@Getter
@Setter
public class QuizResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String topic;
    private String planTitle;
    private int total;
    private int correct;
    private int wrong;
    private int unattempted;
    private int percent;
    private  String createdAt;
    private Boolean showResult;
}