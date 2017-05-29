package ir.rendan.services.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import ir.rendan.model.Question;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Amir Shams on 5/26/2017.
 */
public class QuestionDTO {

    @JsonProperty
    private String username;

    @JsonProperty
    private String body;

    @JsonProperty
    private String ans;


    @JsonProperty
    private Long qId;

    @JsonProperty
    private Long date;



    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public void setqId(Long qId) {
        this.qId = qId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getAns() {
        return ans;
    }

    public void setAns(String ans) {
        this.ans = ans;
    }

    public static List<QuestionDTO> loadFrom(List<Question> list)
    {
        List<QuestionDTO> result = new ArrayList<>();

        for(Question q : list)
        {
            QuestionDTO dto = new QuestionDTO();

            dto.setUsername(q.getUser().getUsername());
            dto.setAns(q.getAns());
            dto.setBody(q.getBody());
            dto.setDate(q.getSubmissionDate().getTime());
            dto.setqId(q.getId());

            result.add(dto);
        }

        return result;
    }
}
