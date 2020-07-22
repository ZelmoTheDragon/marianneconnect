package com.github.zelmothedragon.marianneconnect.keycloak.social;

/**
 * Electronic IDentification Authentication and trust Services.
 *
 * @author MOSELLE Maxime
 */
public enum EIDAS {

    /**
     * Niveau faible.
     */
    LEVEL_1("eidas1"),
    /**
     * Niveau substantiel.
     */
    LEVEL_2("eidas2"),
    /**
     * Niveau fort.
     */
    LEVEL_3("eidas3");

    /**
     * Nom du paramètre d'URL pour inclure le niveau EIDAS lors de la
     * cinématique de connexion sur <i>/authorize</i>.
     */
    public static final String QUERY_PARAM_ACR = "acr_values";

    /**
     * Nom technique du niveau EIDAS.
     */
    private final String value;

    /**
     * Constructeur interne de l'énumération.
     *
     * @param value Nom technique du niveau EIDAS
     */
    private EIDAS(final String value) {
        this.value = value;
    }

    // ------------------------------
    // Accesseurs
    // ------------------------------
    public String getValue() {
        return value;
    }

}
