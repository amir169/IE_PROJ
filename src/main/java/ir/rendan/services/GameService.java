package ir.rendan.services;

import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Amir Shams on 6/24/2017.
 */
@Path("api/games")
@Component
public class GameService {

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listAllPermittedGamesForUser()
    {

        return Response.ok().build();
    }
}
