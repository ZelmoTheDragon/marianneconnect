package com.github.zelmothedragon.marianne.keycloak.social;

/**
 *
 * @author MOSELLE Maxime
 */
public enum Environment {

    INTEGRATION("https://fcp.integ01.dev-franceconnect.fr/api/v1"),
    PRODUCTION("https://app.franceconnect.gouv.fr/api/v1");

    private final String baseURL;

    private Environment(final String baseURL) {
        this.baseURL = baseURL;
    }

    public String getBaseURL() {
        return baseURL;
    }

    public String getAutorizationURL() {
        return baseURL + "/authorize";
    }

    public String getTokenURL() {
        return baseURL + "/token";
    }

    public String getUserInfoURL() {
        return baseURL + "/userinfo";
    }

    public String getLogoutUTL() {
        return baseURL + "/logout";
    }

}
