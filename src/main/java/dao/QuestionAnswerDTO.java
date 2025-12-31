package dao;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class QuestionAnswerDTO {

    private String question;
    private String answer;
    private String topic;

}
