package ir.rendan.services;

import ir.rendan.model.Game;
import ir.rendan.model.League;
import ir.rendan.model.Team;
import ir.rendan.model.TeamGame;
import ir.rendan.repository.GameRepository;
import ir.rendan.repository.LeagueRepository;
import ir.rendan.repository.TeamGameRepository;
import ir.rendan.repository.TeamRepository;
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
    private TeamRepository teamRepository;

    public LeagueService(LeagueRepository leagueRepository, TeamGameRepository teamGameRepository,
                         TeamRepository teamRepository,  GameRepository gameRepository) {
        this.leagueRepository = leagueRepository;
        this.teamGameRepository = teamGameRepository;
        this.gameRepository = gameRepository;
        this.teamRepository = teamRepository;
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
    public Response changeLeagueName(@PathParam("leagueID") int leagueID, String name){
        League league = leagueRepository.findOne(leagueID);
        if(league == null){
            return Response.status(Response.Status.BAD_REQUEST).entity("league not found").build();
        }
        league.setName(name);
        leagueRepository.save(league);
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
    public Response addTeam(@PathParam("leagueID") int leagueID, String teamId){
        League league = leagueRepository.findOne(leagueID);
        if(league == null){
            return Response.status(Response.Status.BAD_REQUEST).entity("league not found").build();
        }
        TeamGame teamGame = teamGameRepository.findByTeamAndGame(teamId,league.getGame().getId());
        if(teamGame == null)
        {
            return Response.status(Response.Status.BAD_REQUEST).entity("team game not found").build();
        }
        league.addTeamGame(teamGame,0.0);
        leagueRepository.save(league);
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
        System.out.println(gameId+leagueName);
        try{
            Game game = gameRepository.findOne(gameId);
            if(game == null){
                return Response.status(Response.Status.BAD_REQUEST).entity("game not found").build();
            }
            League league = new League(game,leagueName);
            leagueRepository.save(league);
            return Response.ok().build();
        }catch (Exception e){
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }


    @POST
    @Path("remove_league/{leagueID}")
    public Response removeLeague(@PathParam("leagueID") int leagueID){
        League league = leagueRepository.findOne(leagueID);
        if(league == null){
            return Response.status(Response.Status.BAD_REQUEST).entity("league not found").build();
        }
        league.setDeleted(true);
        leagueRepository.save(league);
        return Response.ok().build();
    }
}
