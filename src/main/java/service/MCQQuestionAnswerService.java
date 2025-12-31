package service;

import dao.MCQ_OutPutBasedQuestionAnswerRepository;
import model.MCQOutPutBasedQuestionAnswer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
