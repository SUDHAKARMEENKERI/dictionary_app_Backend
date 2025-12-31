package model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class QuestionAnswerResponse {

    private Long id;
    private String question;
    private String answer;
    private String topic;
    private String imageBase64;

    private String mobile;

    private String level;
    private String category;

    private String questionType;

    @Override
    public String toString() {
        return "QuestionAnswerResponse{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", topic='" + topic + '\'' +
                ", imageBase64='" + imageBase64 + '\'' +
                ", mobile='" + mobile + '\'' +
                ", level='" + level + '\'' +
                ", category='" + category + '\'' +
                ", questionType='" + questionType + '\'' +
                '}';
    }
}
