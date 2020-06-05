package fr.moselleacademy.marianne.dataprovider.security;

/**
 *
 * @author MOSELLE Maxime
 */
public final class GrantType {

    /**
     * Type: Mot de passe.
     */
    public static final String PASSWORD = "password";

    /**
     * Type: Jeton de rafraichissement.
     */
    public static final String REFRESH_TOKEN = "refresh_token";

    /**
     * Constructeur interne.
     */
    private GrantType() {
        throw new UnsupportedOperationException("No instance for you");
    }

}
