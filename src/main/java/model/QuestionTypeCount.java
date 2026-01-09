// src/main/java/model/QuestionTypeCount.java
package model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionTypeCount {
    private long mcqCount;
    private long outputBasedCount;
    private long outputBasedMcqCount;

    public QuestionTypeCount(long mcqCount, long outputBasedCount, long outputBasedMcqCount) {
        this.mcqCount = mcqCount;
        this.outputBasedCount = outputBasedCount;
        this.outputBasedMcqCount = outputBasedMcqCount;
    }
}