package it.gabriele.iovino.skillspringapi;

import it.gabriele.iovino.skillspringapi.security.CorsFilter;
import it.gabriele.iovino.skillspringapi.security.JWTTokenNeededFilter;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import jakarta.ws.rs.ApplicationPath;

@Component
@ApplicationPath("api")
public class JerseyConfig extends ResourceConfig{

    public JerseyConfig() {
        register(JWTTokenNeededFilter.class);
        register(CorsFilter.class);
        packages("it.gabriele.iovino.skillspringapi");
    }
}
