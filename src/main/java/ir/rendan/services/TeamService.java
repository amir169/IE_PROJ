package ir.rendan.services;

import ir.rendan.model.Team;
import ir.rendan.model.User;
import ir.rendan.repository.QuestionRepository;
import ir.rendan.repository.TeamRepository;
import ir.rendan.repository.UserRepository;
import ir.rendan.services.dto.TeamRegistrationDTO;
import ir.rendan.util.EmailUtils;
import ir.rendan.util.MessageTranslator;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by ABM on 5/17/2017.
 */
@Path("api/team")
@Component
public class TeamService {

    private final UserRepository userRepository;
    private final MessageTranslator translator;
    private final TeamRepository teamRepository;

    public TeamService(UserRepository userRepository, MessageTranslator translator, TeamRepository teamRepository) {

        this.userRepository = userRepository;
        this.translator = translator;
        this.teamRepository = teamRepository;
    }

    @Path("/register")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response teamRegister(TeamRegistrationDTO dto){

        String name = dto.getName();
        Set<User> members = new HashSet<>();
        for(User u : dto.getMembers())
        {
            User member = userRepository.findByEmail(u.getEmail());
            if(member == null)
                return Response.status(Response.Status.BAD_REQUEST).entity(translator.translate("team.register.failed")).build();
            members.add(member);
        }

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User manager = userRepository.findOne(username);

        Team team = new Team(name, manager, members);

        try {
            teamRepository.save(team);
        }catch (Exception e)
        {
            return Response.status(Response.Status.BAD_REQUEST).entity(translator.translate("team.register.failed")).build();
        }

        for(User u : members)
        {
            //send email
        }

        return Response.ok(translator.translate("team.register.successfully")).build();
    }

    @Path("/get-code")
    @POST
    public Response getCode(){

        return Response.ok().build();
    }

    @Path("/select-code")
    @POST
    public Response selectCode(String name){

        return Response.ok().build();
    }

}

