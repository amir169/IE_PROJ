package ir.rendan.services;

import ir.rendan.model.Question;
import ir.rendan.model.User;
import ir.rendan.repository.QuestionRepository;
import ir.rendan.repository.UserRepository;
import ir.rendan.services.dto.QuestionDTO;
import ir.rendan.util.MessageTranslator;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.List;

/**
 * Created by Amir Shams on 5/26/2017.
 */
@Path("api/question")
@Component
public class QuestionService{

    private final UserRepository userRepository;
    private final MessageTranslator translator;
    private final QuestionRepository questionRepository;

    public QuestionService(UserRepository userRepository, MessageTranslator translator, QuestionRepository questionRepository) {

        this.userRepository = userRepository;
        this.translator = translator;
        this.questionRepository = questionRepository;
    }


    @POST
    @Path("submit")
    public Response submitQuestion(String body){

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.getOne(username);

        Question question = new Question(body,null,user,new Date());

        questionRepository.save(question);

        return Response.ok(translator.translate("question.added.successfully")).build();
    }

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getQuestions(@DefaultValue("0")@QueryParam("start")Integer start,
                                 @DefaultValue("10")@QueryParam("len")Integer len,
                                 @DefaultValue("submissionDate")@QueryParam("order_by")String order,
                                 @DefaultValue("content")@QueryParam("type")String queryType){

        if(queryType.equals("count"))
            return Response.ok(questionRepository.count()).build();

        List<Question> questions = questionRepository.findAll(new PageRequest(start,len,new Sort(Sort.Direction.DESC,order))).getContent();

        List<QuestionDTO> result = QuestionDTO.loadFrom(questions);

        return Response.ok(result).build();
    }

}
