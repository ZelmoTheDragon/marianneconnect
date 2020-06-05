package fr.moselleacademy.marianne.dataprovider.security;

import java.util.Objects;
import java.util.ResourceBundle;

/**
 * Entête des requêtes avec une autorisation.
 *
 * @author MOSELLE Maxime
 */
public final class Authorization {

    /**
     * Instance factice, vide.
     */
    public static final Authorization EMPTY = new Authorization("", "");

    /**
     * Basic.
     */
    public static final String TYPE_BASIC = "Basic";

    /**
     * Bearer.
     */
    public static final String TYPE_BEARER = "Bearer";

    /**
     * Nom de l'entête HTTP de l'autorisation.
     */
    public static final String HEADER_NAME = "Authorization";

    /**
     * Séparateur entre le type et le code.
     */
    private static final String HEADER_SEPARATOR = " ";

    /**
     * Nombre de données.
     */
    private static final int HEADER_LENGTH = 2;

    /**
     * Colonne "type".
     */
    private static final int COLUMN_TYPE = 0;

    /**
     * Colonne "code".
     */
    private static final int COLUMN_CODE = 1;

    /**
     * Type d'autorisation.
     */
    private final String type;

    /**
     * Code d'autorisation.
     */
    private final String code;

    /**
     * Constructeur.
     *
     * @param type Type d'autorisation
     * @param code Code d'autorisation
     */
    public Authorization(final String type, final String code) {
        this.type = type;
        this.code = code;
    }

    /**
     * Convertir l'entête d'autorisation en objet.
     *
     * @param header Entête HTTP
     * @return Une instance d'autorisation
     */
    public static Authorization parse(final String header) {
        Authorization entity;
        if (Objects.isNull(header)) {
            entity = EMPTY;
        } else {
            String[] data = header.split(HEADER_SEPARATOR);
            if (data.length == HEADER_LENGTH) {
                String type = data[COLUMN_TYPE].trim();
                String code = data[COLUMN_CODE].trim();
                entity = new Authorization(type, code);
            } else {
                entity = EMPTY;
            }
        }
        return entity;
    }

    /**
     * Créer une instance d'autorisation avec le code interne du serveur.
     *
     * @param type Type d'autorisation
     * @return Une instance d'autorisation avec le code interne du serveur
     */
    public static Authorization createServerAuth(final String type) {
        String code = ResourceBundle
                .getBundle("application")
                .getString("app.code")
                .trim();

        return new Authorization(type, code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, code);
    }

    @Override
    public boolean equals(Object obj) {
        final boolean eq;
        if (this == obj) {
            eq = true;
        } else if (!(obj instanceof Authorization)) {
            eq = false;
        } else {
            Authorization other = (Authorization) obj;
            eq = Objects.equals(type, other.type)
                    && Objects.equals(code, other.code);
        }
        return eq;
    }

    // ------------------------------
    // Accesseurs
    // ------------------------------
    public String getType() {
        return type;
    }

    public String getCode() {
        return code;
    }

}
