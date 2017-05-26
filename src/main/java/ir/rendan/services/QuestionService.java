package ir.rendan.services;

import ir.rendan.model.Question;
import ir.rendan.model.User;
import ir.rendan.repository.QuestionRepository;
import ir.rendan.repository.UserRepository;
import ir.rendan.services.base.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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

        Question question = new Question(body,null,user);

        questionRepository.save(question);

        return Response.ok(translate("question.added.successfully")).build();
    }

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getQuestions(){

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        List<Question> questions = questionRepository.getCommentsByUser(userRepository.getOne(username));

        return Response.ok(questions).build();
    }

}
