package model;

import jakarta.persistence.*;

@Entity
@Table(name = "questionAnswer")
public class QuestionAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String question;
    private String answer;
    public String topic;

    @Lob
    private byte[] image;

    public Long getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public String getTopic() {
        return topic;
    }

    public byte[] getImage() {
        return image;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "QuestionAnswer{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", topic='" + topic + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
