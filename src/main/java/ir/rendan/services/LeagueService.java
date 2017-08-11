package ir.rendan.services;

import ir.rendan.model.Game;
import ir.rendan.model.League;
import ir.rendan.model.TeamGame;
import ir.rendan.repository.GameRepository;
import ir.rendan.repository.LeagueRepository;
import ir.rendan.repository.TeamGameRepository;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by SalehJFZ on 02/08/2017.
 */
@Path("api/leagues")
@Component
public class LeagueService {

    private GameRepository gameRepository;
    private LeagueRepository leagueRepository;
    private TeamGameRepository teamGameRepository;

    public LeagueService(LeagueRepository leagueRepository, TeamGameRepository teamGameRepository, GameRepository gameRepository) {
        this.leagueRepository = leagueRepository;
        this.teamGameRepository = teamGameRepository;
        this.gameRepository = gameRepository;
    }

    @GET
    @Path("list/{game}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listAllLeagueForThisGame(@PathParam("game") String game){
        //TODO we retrive all league temporary
        Iterator<League> itr =  leagueRepository.findAll().iterator();
        List<League> list  = new ArrayList<>();
        while(itr.hasNext()){
            list.add((League) itr.next());
        }
        return Response.ok().entity(list).build();
    }

    @POST
    @Path("change_name/{leagueID}")
    public Response changeLeagueName(@PathParam("leagueID") long leagueID, String name){
        //TODO
        System.out.println("change name");
        return Response.ok().build();
    }

    @GET
    @Path("scheduler/{type}")
    public Response scheduler(@PathParam("type") int type ){
        System.out.println("sheduler");
        //TODO
        return Response.ok().build();
    }

    @POST
    @Path("add_team/{leagueID}")
    public Response addTeam(@PathParam("leagueID") long layerID, String teamName){
        //TODO
        System.out.println("add team with name"+ teamName);
        return Response.ok().build();
    }


    @POST
    @Path("remove_team/{leagueID}")
    public Response removeTeam(@PathParam("leagueID") long layerID, String teamName){
        //TODO
        System.out.println("remove team with name"+ teamName);
        return Response.ok().build();
    }

    @POST
    @Path("add_league")
    public Response addLeague(@QueryParam("league") String leagueName,@QueryParam("gid") Integer gameId){
        try{
            Game game = gameRepository.findOne(gameId);
            if(game == null){
                return Response.status(Response.Status.BAD_REQUEST).entity("game not found").build();
            }
            League league = new League(game,leagueName);
            leagueRepository.save(league);
            return Response.ok().build();
        }catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }


    @POST
    @Path("remove_league/{leagueName}")
    public Response removeLeague(@PathParam("leagueName") String leagueName){
        //TODO
        System.out.println("remove league "+ leagueName);
        return Response.ok().build();
    }
}
