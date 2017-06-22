package ir.rendan.services;

import ir.rendan.model.Team;
import ir.rendan.model.User;
import ir.rendan.repository.TeamRepository;
import ir.rendan.repository.UserRepository;
import ir.rendan.services.dto.TeamRegistrationDTO;
import ir.rendan.util.EmailUtils;
import ir.rendan.util.MessageTranslator;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.mail.internet.AddressException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashSet;
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
    private final EmailUtils emailUtils;

    public TeamService(UserRepository userRepository, MessageTranslator translator, TeamRepository teamRepository,EmailUtils emailUtils) {

        this.userRepository = userRepository;
        this.translator = translator;
        this.teamRepository = teamRepository;
        this.emailUtils = emailUtils;
    }

    @Path("/register")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response teamRegister(TeamRegistrationDTO dto){

        String name = dto.getName();

        if(teamRepository.exists(name))
            return Response.status(Response.Status.BAD_REQUEST).entity(translator.translate("team.register.name.found")).build();

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
            for(User user : members)
                emailUtils.sendTeamInvitationMail(user,team);
        }
        catch (AddressException e)
        {
            return Response.status(Response.Status.BAD_REQUEST).entity(translator.translate("email.invalid")).build();
        }
        catch (Exception e)
        {
            return Response.status(Response.Status.BAD_REQUEST).entity(translator.translate("team.register.failed")).build();
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

