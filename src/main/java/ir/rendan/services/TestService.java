package ir.rendan.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;


/**
 * Created by Amir Shams on 5/1/2017.
 */
@Path("test")
public class TestService {
    @GET
    public Response test()
    {
        return Response.ok("hello jax-rs").build();
    }
}
