package it.gabriele.iovino.skillspringapi.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.IOException;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.Priority;
import jakarta.ws.rs.NotAuthorizedException;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ResourceInfo;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

import java.security.Key;
import java.util.List;

@JWTTokenNeeded
@Provider
@Priority(Priorities.AUTHENTICATION)
public class JWTTokenNeededFilter implements ContainerRequestFilter {

    @Context
    private ResourceInfo resourceInfo;


    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        Secured annotatedRole = resourceInfo.getResourceMethod().getAnnotation(Secured.class);

        if (annotatedRole == null) {
            annotatedRole = resourceInfo.getResourceClass().getAnnotation(Secured.class);
        }

        String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new NotAuthorizedException("Authorization header must be provided");
        }

        String token = authorizationHeader.substring("Bearer".length()).trim();

        try {
            byte[] secret = "fgvajyukkhjgbvfsdkfgvbshjfgvsbsdaetgvbvsertdgerffvh".getBytes();
            Key key = Keys.hmacShaKeyFor(secret);

            Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            Claims body = claims.getBody();
            String role = body.get("role", String.class);

            int officialRole = Boolean.valueOf(role) ? 1 : 0;

            if (officialRole < annotatedRole.role()) {
                requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
            }
        } catch (Exception e) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity(e.getMessage()).build());
        }
    }

}
