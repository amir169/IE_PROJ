package ir.rendan.services;

import ir.rendan.dao.UserDAO;
import ir.rendan.model.UserInfo;
import ir.rendan.services.dto.RegistrationDTO;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Amir Shams on 5/20/2017.
 */
@Path("api/user")
public class UserService {

    @Autowired
    private UserDAO userDAO;

    @GET
    @Path("test_user")
    public Response userExists(@QueryParam("username") String username)
    {
        //TODO check if user exists
        return Response.ok().build();
    }

    @POST
    @Path("register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(RegistrationDTO dto)
    {
        UserInfo user = new UserInfo();
        user.setEnabled(new Short("1"));
        user.setPassword(dto.getPassword());
        user.setRole("USER");
        user.setUserName(dto.getUsername());

        try{
            userDAO.insert(user);
        }catch (Exception e)
        {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        return Response.ok("user successfully added").build();
    }
}
