package fr.moselleacademy.marianne.dataprovider.security;

import java.util.Objects;
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
 * Pour d'entrée de l'API pour la gestion de l'authentification.
 *
 * @author MOSELLE Maxime
 */
@Path("/security")
public class SecurityEndPoint {

    /**
     * Entrepôt de données.
     */
    @Inject
    private CredentialsRepository repository;

    /**
     * Session applicative.
     */
    @Inject
    private SessionRepository session;

    /**
     * Constructeur par défaut. Requis pour le fonctionnement des technologies
     * de Java EE.
     */
    public SecurityEndPoint() {
        // RAS
    }

    /**
     * Demander un jeton pour accéder aux autres URL de cette API.
     *
     * @param authorization Code applicatif d'autorisation
     * @param grantType Type d'accès
     * @param clientId Identifiant de connexion
     * @param clientSecret Mot de passe (en clair)
     * @param refreshToken Jeton de rafraichissement
     * @return Le jeton de session si les paramètres sont valides, sinon un
     * message d'erreur
     */
    @POST
    @Path("/token")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response authorize(
            @HeaderParam("Authorization") final String authorization,
            @FormParam("grant_type") final String grantType,
            @FormParam("username") final String clientId,
            @FormParam("password") final String clientSecret,
            @FormParam("refresh_token") final String refreshToken) {

        final Response response;
        Authorization clientAuth = Authorization.parse(authorization);
        Authorization serverAuth = Authorization.createServerAuth(Authorization.TYPE_BASIC);

        if (!Objects.equals(serverAuth, clientAuth)) {
            response = createFailure("Invalid Authorization code");
        } else if (Objects.equals(GrantType.PASSWORD, grantType)) {
            response = repository
                    .findByCredentials(clientId, clientSecret)
                    .map(Token::generate)
                    .map(this::storeInSession)
                    .map(SecurityEndPoint::createSuccess)
                    .orElseGet(() -> createFailure("Bad credentials"));

        } else if (Objects.equals(GrantType.REFRESH_TOKEN, grantType)) {

            response = session
                    .findByRefreshToken(refreshToken)
                    .map(SecurityEndPoint::createSuccess)
                    .orElseGet(() -> createFailure("Invalid token"));

        } else {
            response = createFailure("Invalid grant_type");
        }
        return response;
    }

    /**
     * Enregistrer en session le jeton
     *
     * @param token Un jeton de session
     * @return Le jeton initial
     */
    private Token storeInSession(final Token token) {
        session.add(token);
        return token;
    }

    /**
     * Créer un réponse de succès.
     *
     * @param token Un jeton de session
     * @return Un réponse de succès
     */
    private static Response createSuccess(final Token token) {
        return Response
                .ok(token)
                .build();
    }

    /**
     * Créer un réponse d'échec.
     *
     * @param message Message d'erreur
     * @return Un réponse d'échec
     */
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
