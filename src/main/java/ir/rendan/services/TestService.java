package ir.rendan.services;

import ir.rendan.dao.UserDAO;
import ir.rendan.model.UserInfo;
import ir.rendan.services.base.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.Locale;


/**
 * Created by Amir Shams on 5/1/2017.
 */
@Path("test")
public class TestService extends AbstractService{

    @Autowired
    MessageSource messageSource;

    @GET
    @Produces("application/json")
    public Response test()
    {
        UserDAO UD = new UserDAO();
        UserInfo ui = UD.getByUserName("user0");
        return Response.ok().entity(ui).build();
    }

    @GET
    @Path("test")
    @Produces("text/html; charset=UTF-8")
    public Response t()
    {
        return Response.ok(translate("test")).build();
    }
}
