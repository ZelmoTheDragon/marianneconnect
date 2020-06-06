package com.github.zelmothedragon.marianne.keycloak.social;

import java.util.Objects;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import org.keycloak.broker.oidc.OIDCIdentityProvider;
import org.keycloak.broker.oidc.OIDCIdentityProviderConfig;
import org.keycloak.broker.provider.AuthenticationRequest;
import org.keycloak.broker.social.SocialIdentityProvider;
import org.keycloak.events.Errors;
import org.keycloak.events.EventBuilder;
import org.keycloak.events.EventType;
import org.keycloak.jose.jws.JWSInput;
import org.keycloak.jose.jws.crypto.HMACProvider;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.UserSessionModel;
import org.keycloak.services.ErrorPage;
import org.keycloak.services.managers.AuthenticationManager;
import org.keycloak.services.messages.Messages;

/**
 *
 * @author MOSELLE Maxime
 */
public final class FranceConnectIdentityProvider extends OIDCIdentityProvider
        implements SocialIdentityProvider<OIDCIdentityProviderConfig> {

    public static final String DEFAULT_SCOPE = "openid identite_pivot email";

    public FranceConnectIdentityProvider(
            final KeycloakSession session,
            final FranceConnectIdentityProviderConfig config) {

        super(session, config);
    }

    @Override
    public Object callback(
            final RealmModel realm,
            final AuthenticationCallback callback,
            final EventBuilder event) {

        return new FranceConnectEndPoint(callback, realm, event);
    }

    @Override
    public FranceConnectIdentityProviderConfig getConfig() {
        return (FranceConnectIdentityProviderConfig) super.getConfig();
    }

    @Override
    protected UriBuilder createAuthorizationUrl(final AuthenticationRequest request) {
        var authorizationUrlBuilder = super.createAuthorizationUrl(request);
        var authorizationUrlQuery = authorizationUrlBuilder.build().getQuery();

        var eidasLevel = getConfig().getEidasLevel();
        var eidasOverride = getConfig().isEidasOverride();

        if (eidasOverride) {
            if (authorizationUrlQuery.contains(EIDAS.QUERY_PARAM_ACR)) {
                authorizationUrlBuilder.replaceQueryParam(EIDAS.QUERY_PARAM_ACR, eidasLevel);
            } else {
                authorizationUrlBuilder.queryParam(EIDAS.QUERY_PARAM_ACR, eidasLevel);
            }
        } else {
            if (!authorizationUrlQuery.contains(EIDAS.QUERY_PARAM_ACR)) {
                authorizationUrlBuilder.queryParam(EIDAS.QUERY_PARAM_ACR, eidasLevel);
            }
        }
        return authorizationUrlBuilder;
    }

    @Override
    protected boolean verify(final JWSInput jws) {
        final boolean valid;
        if (getConfig().isValidateSignature()) {
            var clientSecret = getConfig().getClientSecret().getBytes();
            valid = HMACProvider.verify(jws, clientSecret);
        } else {
            valid = true;
        }
        return valid;
    }

    @Override
    protected String getDefaultScopes() {
        return DEFAULT_SCOPE;
    }

    protected class FranceConnectEndPoint extends OIDCEndpoint {

        public FranceConnectEndPoint(
                final AuthenticationCallback callback,
                final RealmModel realm,
                final EventBuilder event) {

            super(callback, realm, event);
        }

        @GET
        @Path("logout_response")
        @Override
        public Response logoutResponse(@QueryParam("state") final String state) {
            final Response result;
            if (Objects.isNull(state)) {
                logger.warn("No query parameter 'state', so using cookie");
                var authResult = AuthenticationManager.authenticateIdentityCookie(session, realm, false);
                if (Objects.nonNull(authResult)) {
                    var userSession = authResult.getSession();
                    result = finishLogout(userSession);
                } else {
                    logger.error("user in session not found");
                    sendEventUserSessionNotFound();
                    result = noValidUserSession();
                }
            } else {
                var userSession = session.sessions().getUserSession(realm, state);
                if (Objects.isNull(userSession)) {
                    logger.error("user in session not found, with query parameter 'state'");
                    sendEventUserSessionNotFound();
                    result = noValidUserSession();
                } else if (userSession.getState() != UserSessionModel.State.LOGGING_OUT) {
                    logger.error("usersession in different state");
                    sendEventUserSessionNotFound();
                    result = badRequestWithState();
                } else {
                    result = finishLogout(userSession);
                }
            }
            return result;
        }

        private Response finishLogout(final UserSessionModel userSession) {
            return AuthenticationManager
                    .finishBrowserLogout(
                            session,
                            realm,
                            userSession,
                            session.getContext().getUri(),
                            clientConnection,
                            headers
                    );
        }

        private Response noValidUserSession() {
            return ErrorPage.error(
                    session,
                    null,
                    Response.Status.BAD_REQUEST,
                    Messages.IDENTITY_PROVIDER_UNEXPECTED_ERROR
            );
        }

        private Response badRequestWithState() {
            return ErrorPage.error(
                    session,
                    null,
                    Response.Status.BAD_REQUEST,
                    Messages.SESSION_NOT_ACTIVE
            );
        }

        private void sendEventUserSessionNotFound() {
            var eventBuilder = new EventBuilder(realm, session, clientConnection);
            eventBuilder.event(EventType.LOGOUT);
            eventBuilder.error(Errors.USER_SESSION_NOT_FOUND);
        }

    }

}
