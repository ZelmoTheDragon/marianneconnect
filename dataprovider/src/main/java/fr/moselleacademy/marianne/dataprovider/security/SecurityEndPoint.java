package fr.moselleacademy.marianne.dataprovider.security;

import java.util.Objects;
import java.util.ResourceBundle;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author MOSELLE Maxime
 */
@Path("/security")
public class SecurityEndPoint {

    /**
     * Code d'authorisation applicatif.
     */
    private final String authorizationCode;

    /**
     * Entrepôt de données.
     */
    @Inject
    private SessionRepository repository;

    /**
     * Constructeur par défaut. Requis pour le fonctionnement des technologies
     * de Java EE.
     */
    public SecurityEndPoint() {
        authorizationCode = ResourceBundle
                .getBundle("application")
                .getString("app.code");
    }

    @POST
    @Path("/authorize")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response authorize(
            @HeaderParam("Authorization") final String authorization,
            @FormParam("grant_type") final String grantType,
            @FormParam("username") final String clientId,
            @FormParam("password") final String clientSecret) {

        final Response response;
        if (!Objects.equals(authorizationCode, authorization)) {
            response = createFailure("Invalid Authorization code");
        } else if (!Objects.equals(GrantType.PASSWORD, grantType)) {
            response = createFailure("Invalid grant_type");
        } else {
            response = repository
                    .findByCredentials(clientId, clientSecret)
                    .map(Token::generate)
                    .map(this::storeInSession)
                    .map(SecurityEndPoint::createSuccess)
                    .orElseGet(() -> createFailure("Bad credentials"));
        }
        return response;
    }
    
    private Token storeInSession(Token token){
        repository.storeInSession(token);
        return token;
    }

    private static Response createSuccess(Token token) {
        return Response
                .status(Response.Status.CREATED)
                .entity(token)
                .build();
    }

    private static Response createFailure(final String message) {
        JsonObject fault = Json
                .createObjectBuilder()
                .add("fault", message)
                .build();

        return Response
                .status(Response.Status.UNAUTHORIZED)
                .entity(fault)
                .build();
    }

}
