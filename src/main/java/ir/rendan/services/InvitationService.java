package ir.rendan.services;

import ir.rendan.model.Team;
import ir.rendan.model.User;
import ir.rendan.repository.TeamRepository;
import ir.rendan.util.MessageTranslator;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by Amir Shams on 6/22/2017.
 */
@Path("api/invitation")
@Component
public class InvitationService {

    private final TeamRepository teamRepository;
    private final MessageTranslator translator;

    public InvitationService(TeamRepository teamRepository,MessageTranslator translator) {
        this.teamRepository = teamRepository;
        this.translator = translator;
    }

    @GET
    @Path("accept/{code}")
    @Transactional
    public Response validate(@PathParam("code") String code)
    {
        Team team = teamRepository.getByCode(code);

        if(team == null)
            return Response.status(Response.Status.NOT_FOUND).build();

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        for(User user : team.getInvitedMembers())
        {
            if(user.getUsername().equals(username))
            {
                team.getInvitedMembers().remove(user);
                team.addMember(user);
                team.validate();
                teamRepository.save(team);
                try {
                    return Response.seeOther(new URI("/")).build();
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
            }
        }

        return Response.status(Response.Status.BAD_REQUEST).entity(translator.translate("team.invitation.accept.failed")).build();
    }
}
