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
        Game game = new Game("سرخپوست ها","indians_logo.png",3);

        game.setAvailableUntil(new Date());

        game.setConductor("دانشگاه شهید بهشتی");
        game.setPlace("آنلاین");
        game.setLogoAddress("indians_logo.png");
        game.setMaxTeamSize(3);
        game.setPrice(-1);
        game.setDescriptionAddress("indians_intro.html");
        gameRepository.save(game);

        Game game2 = new Game("مسابقه","game_logo.png",3);

        game2.setAvailableUntil(new Date());

        game2.setConductor("دانشگاه شهید بهشتی");
        game2.setPlace("دانشکده مهندسی کامپیوتر");
        game2.setLogoAddress("game_logo.png");
        game2.setMaxTeamSize(1);
        game2.setPrice(15000);
        game2.setDescriptionAddress("game_intro.html");
        gameRepository.save(game2);

        return Response.ok().build();
    }
}
