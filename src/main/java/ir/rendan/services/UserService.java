package ir.rendan.services;

import ir.rendan.dao.UserDAO;
import ir.rendan.model.UserInfo;
import ir.rendan.services.dto.RegistrationDTO;
import ir.rendan.util.MailSender;
import ir.rendan.util.StringGenerator;
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
        user.setEnabled(new Short("0"));
        user.setPassword(dto.getPassword());
        user.setRole("USER");
        user.setUserName(dto.getUsername());
        user.setEmail(dto.getEmailAddress());
        user.setActivationCode(StringGenerator.generateValidationCode());


        try{
            userDAO.insert(user);
        }catch (Exception e)
        {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        String link = "http://localhost:8080/api/user/validate/" + user.getActivationCode();
        MailSender.sendEmail(user.getEmail(),"Validation Link",link);

        return Response.ok("user successfully added").build();
    }

    @GET
    @Path("validate/{code}")
    public Response validate(@PathParam("code") String code)
    {
        UserInfo user = userDAO.getByActivationCode(code);

        if(user == null)
            return Response.status(Response.Status.BAD_REQUEST).build();

        user.setEnabled(new Short("1"));
        userDAO.update(user);



        return Response.ok("Your account is successfully enabled!\nPlease Login!").build();
    }
}
