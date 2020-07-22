package com.github.zelmothedragon.marianneconnect.keycloak.protocol;

/**
 * Type d'accès.
 *
 * @author MOSELLE Maxime
 */
public final class GrantType {

    /**
     * Nom de l'entête HTTP du type d'accès.
     */
    public static final String HEADER_NAME = "grant_type";

    /**
     * Type: Mot de passe.
     */
    public static final String PASSWORD = "password";

    /**
     * Type: Jeton de rafraichissement.
     */
    public static final String REFRESH_TOKEN = "refresh_token";

    /**
     * Constructeur interne. Pas d'instanciation.
     */
    private GrantType() {
        throw new UnsupportedOperationException("No instance for you");
    }

}
