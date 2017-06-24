package ir.rendan.services;

import ir.rendan.model.Game;
import ir.rendan.model.Team;
import ir.rendan.model.TeamGame;
import ir.rendan.model.User;
import ir.rendan.repository.GameRepository;
import ir.rendan.repository.TeamGameRepository;
import ir.rendan.repository.TeamRepository;
import ir.rendan.services.dto.GameRegistrationDTO;
import ir.rendan.util.MessageTranslator;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Amir Shams on 6/24/2017.
 */
@Path("api/teamgame")
@Component
public class TeamGameService {

    private final TeamRepository teamRepository;
    private final TeamGameRepository teamGameRepository;
    private final GameRepository gameRepository;
    private final MessageTranslator translator;

    public TeamGameService(TeamRepository teamRepository, TeamGameRepository teamGameRepository, GameRepository gameRepository,MessageTranslator translator) {
        this.teamRepository = teamRepository;
        this.teamGameRepository = teamGameRepository;
        this.gameRepository = gameRepository;
        this.translator = translator;
    }

    @POST
    @Path("register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response register(GameRegistrationDTO dto)
    {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        if(dto == null || dto.getGameId() == null || dto.getTeamId() == null)
            return Response.status(Response.Status.BAD_REQUEST).build();

        Team team = teamRepository.findOne(dto.getTeamId());
        Game game = gameRepository.findOne(Integer.valueOf(dto.getGameId()));

        if(team == null || game == null)
            return Response.status(Response.Status.BAD_REQUEST).build();

        if(team.getMembers().size() > game.getMaxTeamSize())
            return Response.status(Response.Status.BAD_REQUEST).build();

        if(!team.getManager().getUsername().equals(username) || team.getValidated() == 0)
            return Response.status(Response.Status.FORBIDDEN).build();

        for(User u : team.getMembers())
        {
            if(!teamGameRepository.isRegistered(u.getUsername(),game.getId()).isEmpty())
                return Response.status(Response.Status.BAD_REQUEST).entity(translator.translate("game.register.member.already.exists")).build();
        }

        TeamGame teamGame = new TeamGame(team,game);

        teamGameRepository.save(teamGame);

        return Response.ok().build();
    }
}
