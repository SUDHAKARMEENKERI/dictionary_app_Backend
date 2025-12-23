package model;

import jakarta.persistence.*;

import java.util.Arrays;

@Entity
@Table(name = "questionAnswer")
public class QuestionAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String question;
    private String answer;
    public String topic;

    private String mobile;

    @Lob
    @Basic(fetch = FetchType.EAGER)
    private byte[] image;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

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
                ", mobile='" + mobile + '\'' +
                ", image=" + Arrays.toString(image) +
                '}';
    }
}
