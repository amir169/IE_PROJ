package ir.rendan.config;

//import ir.rendan.TestService;
import ir.rendan.TestService;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletProperties;
import org.springframework.stereotype.Component;

/**
 * Created by Amir Shams on 5/1/2017.
 */
@Component
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {

        register(CORSResponseFilter.class);
        register(TestService.class);

        property(ServletProperties.FILTER_FORWARD_ON_404, true);
    }

}
