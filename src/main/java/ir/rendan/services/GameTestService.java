package ir.rendan.services;

import ir.rendan.model.Game;
import ir.rendan.repository.GameRepository;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.Date;

/**
 * Created by Amir Shams on 6/23/2017.
 */
@Path("api/test")
@Component
public class GameTestService {

    private final GameRepository gameRepository;

    public GameTestService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Path("game")
    @GET
    public Response test()
    {
        Game game = new Game("سرخپوست ها","games/indians",3);

        game.setAvailableUntil(new Date());

        game.setConductor("دانشگاه شهید بهشتی");
        game.setPlace("آنلاین");
        game.setLogoAddress("elements/Game_logo.png");

        game.setMaxTeamSize(3);

        gameRepository.save(game);

        return Response.ok().build();
    }
}
