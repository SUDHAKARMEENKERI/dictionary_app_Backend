package model;

import jakarta.persistence.*;

@Entity
@Table(name = "word_meaning")

public class WordMeaning {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true,name = "word")
    private String word;

    @Column(nullable = false,name = "meaning")
    private String meaning;

    public Long getId() {
        return id;
    }

    public String getWord() {
        return word;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    @Override
    public String toString() {
        return "WordMeaning{" +
                "id=" + id +
                ", word='" + word + '\'' +
                ", meaning='" + meaning + '\'' +
                '}';
    }
}
