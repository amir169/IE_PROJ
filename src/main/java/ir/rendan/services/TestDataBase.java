package ir.rendan.services;

import ir.rendan.config.HibernateUtils;
import ir.rendan.model.TestEntity;
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
        String res = "";
        Session s = HibernateUtils.getSession();
        Criteria c = s.createCriteria(TestEntity.class);
        List<TestEntity> l = c.list();

        for (TestEntity aL : l)
            res += (aL.getName() + "\n");

        return Response.ok(res).build();
    }
}
