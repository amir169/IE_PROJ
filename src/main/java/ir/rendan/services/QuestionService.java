package ir.rendan.services;

import ir.rendan.model.Question;
import ir.rendan.model.User;
import ir.rendan.repository.QuestionRepository;
import ir.rendan.repository.UserRepository;
import ir.rendan.services.base.AbstractService;
import ir.rendan.services.dto.QuestionDTO;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.PagedResources;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.List;

/**
 * Created by Amir Shams on 5/26/2017.
 */
@Path("api/question")
public class QuestionService extends AbstractService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QuestionRepository questionRepository;


    @POST
    @Path("submit")
    public Response submitQuestion(String body){

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.getOne(username);

        Question question = new Question(body,null,user,new Date());

        questionRepository.save(question);

        return Response.ok(translate("question.added.successfully")).build();
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

    @POST
    @Path("reply")
    @Produces(MediaType.APPLICATION_JSON)
    public Response setReply(String body){
        System.out.println(body);
        JSONObject obj = new JSONObject(body);
        Long qId = obj.getLong("qId");
        String answerText = obj.getString("message");
        Question question = questionRepository.findOne(qId);
        question.setAns(answerText);

        questionRepository.save(question);

        return Response.ok("ok").build();
    }

    @GET
    @Path("remove-user")
    @Produces(MediaType.APPLICATION_JSON)
    public Response removeUser(@DefaultValue("")@QueryParam("userName")String userName){

        User user = userRepository.getOne(userName);

        userRepository.delete(user);

        return Response.ok().build();
    }

}
