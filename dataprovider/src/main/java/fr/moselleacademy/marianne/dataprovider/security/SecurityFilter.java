package fr.moselleacademy.marianne.dataprovider.security;

import java.io.IOException;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

/**
 * Filtre des requêtes HTTP entrante sur l'API, vérifie que l'application
 * cliente est connectée.
 *
 * @author MOSELLE Maxime
 */
@Provider
public class SecurityFilter implements ContainerRequestFilter {

    /**
     * Journalisation.
     */
    private static final Logger LOGGER = Logger.getLogger(SecurityFilter.class.getName());

    /**
     * URL de l'API qui sont couvert par ce filtre.
     */
    private static final String URL_PATTERN = "/agent";

    /**
     * Session applicative.
     */
    @Inject
    private SessionRepository session;

    /**
     * Constructeur par défaut. Requis pour le fonctionnement des technologies
     * de Java EE.
     */
    public SecurityFilter() {
        // RAS
    }

    @Override
    public void filter(final ContainerRequestContext request) throws IOException {
        String path = request.getUriInfo().getPath();
        if (path.contains(URL_PATTERN)) {
            String authorisation = request.getHeaderString(Authorization.HEADER_NAME);
            Authorization clientAuth = Authorization.parse(authorisation);
            Token token = session
                    .findByAccessToken(clientAuth.getCode())
                    .orElseThrow(() -> new WebApplicationException("Access denied", Response.Status.UNAUTHORIZED));

            LOGGER.fine(() -> "Authorized clientId: " + token.getCredentials().getClientId());
        }
    }

}
