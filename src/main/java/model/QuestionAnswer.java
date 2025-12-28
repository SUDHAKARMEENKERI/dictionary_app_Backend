package model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;

@Entity
@Table(name = "questionAnswer")
@Getter
@Setter
public class QuestionAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String question;
    private String answer;
    public String topic;
    private String mobile;
    private String questionType;

    @Lob
    @Basic(fetch = FetchType.EAGER)
    private byte[] image;

    @Override
    public String toString() {
        return "QuestionAnswer{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", topic='" + topic + '\'' +
                ", mobile='" + mobile + '\'' +
                ", questionType='" + questionType + '\'' +
                ", image=" + Arrays.toString(image) +
                '}';
    }
}
