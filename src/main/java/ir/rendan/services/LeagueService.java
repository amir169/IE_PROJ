package ir.rendan.services;

import org.springframework.stereotype.Service;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

/**
 * Created by SalehJFZ on 02/08/2017.
 */
@Path("api/leagues")
@Service
public class LeagueService {

    @GET
    @Path("list/{game}")
    public Response listAllLeagueForThisGame(@PathParam("game") String game){
        System.out.println("listgame");
        //TODO
        return Response.ok().build();
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
    @Path("add_league/{leagueName}")
    public Response addLeague(String leagueName){
        //TODO
        System.out.println("add league" +leagueName);
        return Response.ok().build();
    }


    @POST
    @Path("remove_league/{leagueName}")
    public Response removeLeague(String leagueName){
        //TODO
        System.out.println("remove league "+ leagueName);
        return Response.ok().build();
    }
}
