package ir.rendan.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * Created by Amir Shams on 5/1/2017.
 */
@Path("api/test")
public class AnotherTest {
    @GET
    public Response test()
    {
        return Response.ok("test successful").build();
    }
}
