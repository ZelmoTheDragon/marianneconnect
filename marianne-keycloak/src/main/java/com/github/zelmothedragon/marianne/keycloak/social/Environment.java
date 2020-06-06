package com.github.zelmothedragon.marianne.keycloak.social;

/**
 * Environnement disponible pour FranceConnect.
 *
 * @author MOSELLE Maxime
 */
public enum Environment {

    /**
     * Environnement d'intégration.
     */
    INTEGRATION("https://fcp.integ01.dev-franceconnect.fr/api/v1"),
    /**
     * Environnement de production.
     */
    PRODUCTION("https://app.franceconnect.gouv.fr/api/v1");

    /**
     * Base de l'URL de l'environnement.
     */
    private final String baseURL;

    /**
     * Constructeur interne de l'énumération.
     *
     * @param baseURL Base de l'URL de l'environnement
     */
    private Environment(final String baseURL) {
        this.baseURL = baseURL;
    }

    // ------------------------------
    // Accesseurs
    // ------------------------------
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
