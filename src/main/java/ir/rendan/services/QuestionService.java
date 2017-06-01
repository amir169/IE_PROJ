package ir.rendan.services;

import ir.rendan.model.Question;
import ir.rendan.model.User;
import ir.rendan.repository.QuestionRepository;
import ir.rendan.repository.UserRepository;
import ir.rendan.util.MessageTranslator;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Date;

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

        User user = userRepository.findOne(username);

        Question question = new Question(body,null,user,new Date());

        questionRepository.save(question);

        return Response.ok(translator.translate("question.added.successfully")).build();
    }

}
