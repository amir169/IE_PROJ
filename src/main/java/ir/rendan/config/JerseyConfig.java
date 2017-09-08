package ir.rendan.config;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
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
        register(MultiPartFeature.class);

//        packages("ir.rendan.services");

        register(ir.rendan.services.QuestionService.class);
        register(ir.rendan.services.UserService.class);
        register(ir.rendan.services.FileUploadService.class);
        register(ir.rendan.services.TeamService.class);
        register(ir.rendan.services.InvitationService.class);
        register(ir.rendan.services.GameTestService.class);
        register(ir.rendan.services.TeamGameService.class);
        register(ir.rendan.services.GameService.class);
        register(ir.rendan.services.LeagueService.class);
        register(ir.rendan.services.CodeService.class);

        property(ServletProperties.FILTER_FORWARD_ON_404, true);
    }

}
