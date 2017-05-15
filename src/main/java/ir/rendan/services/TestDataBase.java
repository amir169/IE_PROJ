package ir.rendan.services;

import ir.rendan.config.HibernateUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by Amir Shams on 5/1/2017.
 */
@Path("api/test_database")
public class TestDataBase {
    @GET
    public Response test()
    {
        return Response.ok().build();
    }
}
