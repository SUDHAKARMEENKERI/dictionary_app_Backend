package service;

import dao.QuestionAnswerRepository;
import model.QuestionAnswer;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class QuestionAnswerService {

    private final QuestionAnswerRepository repository;

    public QuestionAnswerService(QuestionAnswerRepository repo){
        this.repository = repo;
    }

    public QuestionAnswer saveQA(String question, String answer, String topic, MultipartFile image) throws IOException {
        QuestionAnswer qa = new QuestionAnswer();
        qa.setQuestion(question);
        qa.setAnswer(answer);
        qa.setTopic(topic);

        if (image != null && !image.isEmpty()) {
            qa.setImage(image.getBytes());   // 👈 store binary directly
        }

        return repository.save(qa);
    }
}
