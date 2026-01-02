package Helper;

import model.QuestionAnswer;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.InputStream;
import java.util.*;

public class ExcelHelper {
    public static List<QuestionAnswer> parse(InputStream is) {
        try (Workbook workbook = new XSSFWorkbook(is)) {

            Sheet sheet = workbook.getSheetAt(0);
            DataFormatter formatter = new DataFormatter();

            Iterator<Row> rows = sheet.iterator();
            if (!rows.hasNext()) {
                throw new RuntimeException("Excel sheet is empty");
            }

            // 1️⃣ Read header row
            Row headerRow = rows.next();
            Map<String, Integer> headerMap = new HashMap<>();

            for (Cell cell : headerRow) {
                String header = formatter
                        .formatCellValue(cell)
                        .trim()
                        .toLowerCase();
                headerMap.put(header, cell.getColumnIndex());
            }

            // 2️⃣ Validate mandatory headers
            List<String> required = List.of("mobile", "question", "answer");
            for (String col : required) {
                if (!headerMap.containsKey(col)) {
                    throw new RuntimeException(
                            "Missing mandatory column: " + col
                    );
                }
            }

            // 3️⃣ Read data rows
            List<QuestionAnswer> list = new ArrayList<>();

            while (rows.hasNext()) {
                Row row = rows.next();

                QuestionAnswer qa = new QuestionAnswer();

                qa.setMobile(
                        formatter.formatCellValue(
                                row.getCell(headerMap.get("mobile"))
                        )
                );

                qa.setQuestion(
                        formatter.formatCellValue(
                                row.getCell(headerMap.get("question"))
                        )
                );

                qa.setAnswer(
                        formatter.formatCellValue(
                                row.getCell(headerMap.get("answer"))
                        )
                );

                // topic is optional
//                if (headerMap.containsKey("topic")) {
//                    qa.setTopic(
//                            formatter.formatCellValue(
//                                    row.getCell(headerMap.get("topic"))
//                            )
//                    );
//                }

                qa.setTopic(
                        formatter.formatCellValue(
                                row.getCell(headerMap.get("topic"))
                        )
                );

                qa.setCategory(
                        formatter.formatCellValue(
                                row.getCell(headerMap.get("category"))
                        )
                );
                qa.setLevel(
                        formatter.formatCellValue(
                                row.getCell(headerMap.get("level"))
                        )
                );
                qa.setQuestionType(
                        formatter.formatCellValue(
                                row.getCell(headerMap.get("questionType"))
                        )
                );


                list.add(qa);
            }

            return list;

        } catch (Exception e) {
            throw new RuntimeException("Invalid Excel file: " + e.getMessage(), e);
        }
    }

}
