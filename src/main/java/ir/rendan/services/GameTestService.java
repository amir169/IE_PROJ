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
        Game game = new Game("سرخپوست ها","C:\\Users\\SalehJFZ\\Desktop\\Desktop\\Net Eng\\net eng\\IE_PROJ\\src\\main\\resources\\static\\games\\indians",3);

        game.setAvailableUntil(new Date());

        game.setConductor("دانشگاه شهید بهشتی");
        game.setPlace("آنلاین");
        game.setLogoAddress("C:\\Users\\SalehJFZ\\Desktop\\Desktop\\Net Eng\\net eng\\IE_PROJ\\src\\main\\resources\\static\\games\\indians\\elements\\Game_logo.png");
        game.setMaxTeamSize(3);
        game.setPrice(-1);
        gameRepository.save(game);

        Game game2 = new Game("سرخپوست ها2","games/indians",3);

        game2.setAvailableUntil(new Date());

        game2.setConductor("دانشگاه شهید بهشتی");
        game2.setPlace("آنلاین");
        game2.setLogoAddress("elements/Game_logo.png");
        game2.setMaxTeamSize(3);
        game2.setPrice(15000);
        gameRepository.save(game2);

        return Response.ok().build();
    }
}
