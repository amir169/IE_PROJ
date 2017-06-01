package ir.rendan.services.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import ir.rendan.model.Question;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ABM on 5/29/2017.
 */
public class AnswerDTO {

    @JsonProperty("id")
    private Long questionId;

    @JsonProperty("answer")
    private String answerText;

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public String getAnswerText() {
        return answerText;
    }



}




