package ir.rendan.services;

import ir.rendan.model.Game;
import ir.rendan.repository.GameRepository;
import ir.rendan.repository.TeamGameRepository;
import ir.rendan.services.dto.GameDTO;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Amir Shams on 6/24/2017.
 */
@Path("api/games")
@Component
public class GameService {

    private final TeamGameRepository teamGameRepository;
    private final GameRepository gameRepository;

    public GameService(TeamGameRepository teamGameRepository,GameRepository gameRepository) {
        this.teamGameRepository = teamGameRepository;
        this.gameRepository = gameRepository;
    }

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listAllPermittedGamesForUser()
    {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        Iterable<Game> games = gameRepository.findAll();

        List<GameDTO> result = new ArrayList<>();

        for (Game game : games) {
            GameDTO dto = GameDTO.loadFrom(game);
            if(!teamGameRepository.isRegistered(username,game.getId()).isEmpty())
                dto.setPermitted(true);
            else
                dto.setPermitted(false);

            result.add(dto);
        }

        return Response.ok(result).build();
    }
}
