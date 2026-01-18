package service;

import dao.MCQ_OutPutBasedQuestionAnswerRepository;
import errorHandle.ResourceNotFoundException;
import model.MCQOutPutBasedQuestionAnswer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MCQQuestionAnswerService {
    @Autowired
    private MCQ_OutPutBasedQuestionAnswerRepository repository;

    public MCQOutPutBasedQuestionAnswer save(MCQOutPutBasedQuestionAnswer req){
        MCQOutPutBasedQuestionAnswer res = new MCQOutPutBasedQuestionAnswer();

//        res.setAnswer(req.getAnswer());
//        res.setQuestion(req.getQuestion());
//        res.setMobile(req.getMobile());
//        res.setCorrectAnswer(req.getCorrectAnswer());
//        res.setOptionA(req.getOptionA());
//        res.setOptionB(req.getOptionB());
//        res.setOptionC(req.getOptionC());
//        res.setOptionD(req.getOptionD());
//        res.setCorrectAnswer(req.getAnswer());

        return repository.save(req);
    }

//    public List<MCQOutPutBasedQuestionAnswer> getAll(){
//        return repository.findAll();
//    }

    public List<MCQOutPutBasedQuestionAnswer> getMcqQAByParams(String questionType,
                                                               String topic,
                                                               Long category ){
        return repository.findMcqQAByParams(questionType,topic,category);
    }

    public MCQOutPutBasedQuestionAnswer updateMCQQA(Long id, MCQOutPutBasedQuestionAnswer mcqQA){
        MCQOutPutBasedQuestionAnswer res = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Record not found with id: " + id));

        res.setAnswer(mcqQA.getAnswer());
        res.setQuestion(mcqQA.getQuestion());
        res.setMobile(mcqQA.getMobile());
        res.setCorrectAnswer(mcqQA.getCorrectAnswer());
        res.setCode(mcqQA.getCode());
        res.setOptions(mcqQA.getOptions());
        res.setLevel(mcqQA.getLevel());
        res.setTopic(mcqQA.getTopic());
        res.setQuestionType(mcqQA.getQuestionType());
        res.setAdmin(mcqQA.isAdmin());
        res.setCategory(mcqQA.getCategory());

        return repository.save(res);
    }

    public void deleteMCQ(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("MCQ QnA not found with ID " + id);
        }
        repository.deleteById(id);
    }
}
