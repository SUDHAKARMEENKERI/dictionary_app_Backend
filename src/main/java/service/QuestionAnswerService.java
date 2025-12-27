package service;

import Helper.ExcelHelper;
import dao.QuestionAnswerRepository;
import errorHandle.ResourceNotFoundException;
import model.DropdownResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import model.QuestionAnswer;
import model.QuestionAnswerResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.*;

@Service
public class QuestionAnswerService {

    private final QuestionAnswerRepository repository;
    private static final String UPLOAD_DIR = "uploads";

    public QuestionAnswerService(QuestionAnswerRepository repo){
        this.repository = repo;
    }

    public QuestionAnswer saveQA(String question, String answer, String topic,String mobile, MultipartFile image) throws IOException {
        QuestionAnswer qa = new QuestionAnswer();
        qa.setQuestion(question);
        qa.setAnswer(answer);
        qa.setTopic(topic);
        qa.setMobile(mobile);

        if (image != null && !image.isEmpty()) {
            qa.setImage(image.getBytes());   // 👈 store binary directly
        }

        return repository.save(qa);
    }

    public QuestionAnswer getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Data not found"));
    }

    public List<QuestionAnswer> getAll() {
        return repository.findAll();
    }

    public QuestionAnswer updateQA(
            Long id,
            String question,
            String answer,
            String topic,
            String mobile,
            MultipartFile image) throws IOException {

        QuestionAnswer qa = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("QA not found"));

        qa.setQuestion(question);
        qa.setAnswer(answer);
        qa.setTopic(topic);
        qa.setMobile(mobile);

        // 🔥 only update image if new image is sent
        if (image != null && !image.isEmpty()) {
            qa.setImage(image.getBytes());
        } else{
            qa.setImage(null);
        }

        return repository.save(qa);
    }


    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Question not found with ID " + id);
        }
        repository.deleteById(id);
    }

    public long getTotalRecords() {
        return repository.count();
    }

    @Transactional(readOnly = true)
    public List<QuestionAnswerResponse> getByMobile(String mobile) {

        return repository.findByMobile(mobile).stream()
                .map(q -> {
                    QuestionAnswerResponse dto = new QuestionAnswerResponse();

                    dto.setId(q.getId());
                    dto.setQuestion(q.getQuestion());
                    dto.setAnswer(q.getAnswer());
                    dto.setTopic(q.getTopic());
                    dto.setMobile(q.getMobile());

                    if (q.getImage() != null) {
                        dto.setImageBase64(
                                Base64.getEncoder().encodeToString(q.getImage())
                        );
                    }

                    return dto;
                })
                .toList();
    }
    @Transactional
    public void bulkUpload(MultipartFile excelFile) {

        if (excelFile.isEmpty()) {
            throw new RuntimeException("Excel file is empty");
        }

        try {
            List<QuestionAnswer> list =
                    ExcelHelper.parse(excelFile.getInputStream());

            repository.saveAll(list);

        } catch (Exception e) {
            throw new RuntimeException("Bulk upload failed: " + e.getMessage(), e);
        }
    }

    public Page<QuestionAnswer> getPaginated(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        return repository.findAll(pageable);
    }
    public List<?> getByTopic(String topic) {
        return repository.findQAByTopic(topic);
    }


}
