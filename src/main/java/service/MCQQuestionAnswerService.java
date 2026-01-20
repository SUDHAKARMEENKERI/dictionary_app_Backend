package service;

import dao.MCQ_OutPutBasedQuestionAnswerRepository;
import errorHandle.ResourceNotFoundException;
import model.MCQOutPutBasedQuestionAnswer;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.*;

@Service
public class MCQQuestionAnswerService {
    @Autowired
    private MCQ_OutPutBasedQuestionAnswerRepository repository;

    public MCQOutPutBasedQuestionAnswer save(MCQOutPutBasedQuestionAnswer req){
        MCQOutPutBasedQuestionAnswer res = new MCQOutPutBasedQuestionAnswer();

        return repository.save(req);
    }

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

    public int bulkUploadFromExcel(MultipartFile file) {
        List<MCQOutPutBasedQuestionAnswer> mcqList = new ArrayList<>();
        try (InputStream is = file.getInputStream()) {
            Workbook workbook = WorkbookFactory.create(is);
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            if (!rowIterator.hasNext()) return 0; // No data

            // Read header row
            Row headerRow = rowIterator.next();
            List<String> headers = new ArrayList<>();
            for (Cell cell : headerRow) {
                headers.add(cell.getStringCellValue().trim());
            }

            // For each data row
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Map<String, String> rowData = new HashMap<>();
                for (int i = 0; i < headers.size(); i++) {
                    Cell cell = row.getCell(i);
                    String value = "";
                    if (cell != null) {
                        if (cell.getCellType() == CellType.NUMERIC) {
                            value = String.valueOf((long)cell.getNumericCellValue());
                        } else {
                            value = cell.getStringCellValue();
                        }
                    }
                    rowData.put(headers.get(i), value);
                }
                MCQOutPutBasedQuestionAnswer mcq = mapRowToMCQ(rowData);
                mcqList.add(mcq);
            }
            repository.saveAll(mcqList);
            return mcqList.size();
        } catch (Exception e) {
            throw new RuntimeException("Failed to process Excel file: " + e.getMessage(), e);
        }
    }

    // Map row data to MCQOutPutBasedQuestionAnswer, supporting dynamic columns
    private MCQOutPutBasedQuestionAnswer mapRowToMCQ(Map<String, String> rowData) {
        MCQOutPutBasedQuestionAnswer mcq = new MCQOutPutBasedQuestionAnswer();
        // Example: handle both "optionA"/"optA" or "options" as a comma-separated string
        mcq.setCode(rowData.getOrDefault("code", ""));
        mcq.setAnswer(rowData.getOrDefault("answer", ""));
        mcq.setCorrectAnswer(rowData.getOrDefault("correctAnswer", ""));
        mcq.setQuestionType(rowData.getOrDefault("questionType", ""));
        mcq.setCategory(String.valueOf(rowData.containsKey("category") ? Long.valueOf(rowData.get("category")) : null));
        mcq.setTopic(rowData.getOrDefault("topic", ""));
        mcq.setQuestion(rowData.getOrDefault("question", ""));
        mcq.setLevel(rowData.getOrDefault("level", ""));
        mcq.setMobile(rowData.getOrDefault("mobile", ""));

        // Handle options: either as separate columns or as a single "options" column
        List<String> options = new ArrayList<>();
        if (rowData.containsKey("options")) {
            String[] opts = rowData.get("options").split(",");
            for (String opt : opts) options.add(opt.trim());
        } else {
            for (String key : Arrays.asList("optionA", "optionB", "optionC", "optionD", "optA", "optB", "optC", "optD")) {
                if (rowData.containsKey(key)) options.add(rowData.get(key));
            }
        }
        mcq.setOptions(options);

        return mcq;
    }
}
