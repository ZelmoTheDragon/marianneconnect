package com.github.zelmothedragon.marianne.keycloak.protocol;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.ws.rs.core.MediaType;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jboss.logging.Logger;

/**
 * Fournisseur de données externe. Intéroge le fournisseur de données externe
 * sous forme d'appel <i>REST</i>.
 *
 * @author MOSELLE Maxime
 */
final class ExternalDataProvider {

    /**
     * Journalisation.
     */
    private static final Logger LOGGER = Logger.getLogger(ExternalDataProvider.class);

    /**
     * Configuration.
     */
    private final ExternalClaimConfig config;
    
    /**
     * Constructeur à visibilité restreinte. Construit le contrôleur du
     * fournisseur de données externe pour récupérer les informations.
     *
     * @param config Configuration
     */
    ExternalDataProvider(final ExternalClaimConfig config) {
        this.config = config;
    }

    String getExternalClaims() {
        return null;
    }

    private void callAccessToken() {

        String code = config.getKey();
        Authorization authorization = new Authorization(Authorization.TYPE_BASIC, code);
        BasicNameValuePair grantType = new BasicNameValuePair("grant_type", GrantType.PASSWORD);
        BasicNameValuePair username = new BasicNameValuePair("username", config.getClientId());
        BasicNameValuePair password = new BasicNameValuePair("password", config.getClientSecret());

        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(
                Arrays.asList(grantType, username, password),
                StandardCharsets.UTF_8
        );

        HttpPost req = new HttpPost(config.getTokenUrl());
        req.setHeader("Authorization", authorization.toString());
        req.setHeader("Content-Type", MediaType.APPLICATION_FORM_URLENCODED);
        req.setEntity(entity);

        JsonObject json = sendRequest(req);
    }

    private void callRefreshToken() {
        String code = config.getKey();
        Authorization authorization = new Authorization(Authorization.TYPE_BASIC, code);
        BasicNameValuePair grantType = new BasicNameValuePair("grant_type", GrantType.REFRESH_TOKEN);
        BasicNameValuePair refreshToken = new BasicNameValuePair("refresh_token", "");

        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(
                Arrays.asList(grantType, refreshToken),
                StandardCharsets.UTF_8
        );

        HttpPost req = new HttpPost(config.getTokenUrl());
        req.setHeader("Authorization", authorization.toString());
        req.setHeader("Content-Type", MediaType.APPLICATION_FORM_URLENCODED);
        req.setEntity(entity);

        JsonObject json = sendRequest(req);
    }

    private void callEndpoint(final String email) {
        try {
            Authorization authorization = new Authorization(Authorization.TYPE_BEARER, "");
            URI uri = new URIBuilder(config.getClaimsUrl())
                    .setParameter("email", email)
                    .build();

            HttpGet req = new HttpGet(uri);
            req.setHeader("Authorization", authorization.toString());
            req.setHeader("Content-Type", MediaType.APPLICATION_JSON);

            JsonObject json = sendRequest(req);
        } catch (URISyntaxException ex) {
            LOGGER.error("Request failed for URI", ex);
        }
    }

    private static JsonObject sendRequest(final HttpRequestBase req) {
        JsonObject json;
        try (CloseableHttpResponse resp = HttpClients.createDefault().execute(req)) {
            HttpEntity result = resp.getEntity();
            String rawData = EntityUtils.toString(result, StandardCharsets.UTF_8);
            try (JsonReader reader = Json.createReader(new StringReader(rawData))) {
                json = reader.readObject();
            }
        } catch (IOException ex) {
            LOGGER.error("Request failed for URI: " + req.getURI(), ex);
            json = Json.createObjectBuilder().build();
        }
        return json;
    }

}
