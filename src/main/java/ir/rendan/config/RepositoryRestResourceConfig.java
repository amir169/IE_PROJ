package ir.rendan.config;

import ir.rendan.model.Question;
import ir.rendan.model.User;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.mapping.RepositoryDetectionStrategy;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.http.MediaType;

/**
 * Created by Amir Shams on 6/1/2017.
 */

@Configuration
public class RepositoryRestResourceConfig extends RepositoryRestConfigurerAdapter {
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(Question.class,User.class);
        config.setDefaultMediaType(MediaType.APPLICATION_JSON);
        config.setBasePath("items");
        config.setRepositoryDetectionStrategy(RepositoryDetectionStrategy.RepositoryDetectionStrategies.ANNOTATED);
        config.setMaxPageSize(10);
    }
}

