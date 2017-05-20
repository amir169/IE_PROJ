package ir.rendan.services;

import ir.rendan.dao.UserDAO;
import ir.rendan.model.UserInfo;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;


/**
 * Created by Amir Shams on 5/1/2017.
 */
@Path("test")
public class TestService {
    @GET
    @Produces("application/json")
    public Response test()
    {
        UserDAO UD = new UserDAO();
        UserInfo ui = UD.getByUserName("user0");

        return Response.ok().entity(ui).build();
    }

}
