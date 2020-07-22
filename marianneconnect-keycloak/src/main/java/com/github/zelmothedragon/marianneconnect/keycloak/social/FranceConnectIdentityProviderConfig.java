package com.github.zelmothedragon.marianneconnect.keycloak.social;

import org.keycloak.broker.oidc.OIDCIdentityProviderConfig;
import org.keycloak.models.IdentityProviderModel;

/**
 * Configuration du fournisseur d'identité Keycloak.
 *
 * @author MOSELLE Maxime
 */
public final class FranceConnectIdentityProviderConfig extends OIDCIdentityProviderConfig {

    /**
     * Numéro de série.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Configuration pour le niveau EIDAS.
     */
    public static final String CONFIG_EIDAS_LEVEL = "eidasLevel";

    /**
     * Configuration indiquant si oui ou non Keycloak définit lui-même le niveau
     * EIDAS.
     */
    public static final String CONFIG_EIDAS_OVERRIDE = "eidasOverride";

    /**
     * Environnement cible de FranceConnect.
     */
    public static final String CONFIG_ENVIRONMENT = "environment";

    /**
     * Constructeur à visibilité restreinte. Construit la configuration du
     * fournisseur d'identité FranceConnect.
     */
    FranceConnectIdentityProviderConfig() {
        this(null);
    }

    /**
     * Constructeur à visibilité restreinte. Construit la configuration du
     * fournisseur d'identité FranceConnect.
     *
     * @param model Configuration héritée pour ce fournisseur d'identité
     */
    FranceConnectIdentityProviderConfig(final IdentityProviderModel model) {
        super(model);

        // Valeur par défaut du niveau EIDAS
        if (!getConfig().containsKey(CONFIG_EIDAS_LEVEL)) {
            setEidasLevel(EIDAS.LEVEL_3);
        }
        // Valeur par défaut de la surcharge du niveau EIDAS
        if (!getConfig().containsKey(CONFIG_EIDAS_OVERRIDE)) {
            setEidasOverride(true);
        }
        // Valeur par défaut de l'environnement
        if (!getConfig().containsKey(CONFIG_ENVIRONMENT)) {
            setEnvironment(Environment.INTEGRATION);
        }

        // Définir les URL en fonction de l'environnement
        var env = getEnvironment();
        setAuthorizationUrl(env.getAutorizationURL());
        setTokenUrl(env.getTokenURL());
        setUserInfoUrl(env.getUserInfoURL());
        setLogoutUrl(env.getUserInfoURL());
    }

    // ------------------------------
    // Accesseurs & Muttateurs
    // ------------------------------
    public EIDAS getEidasLevel() {
        return EIDAS.valueOf(getConfig().get(CONFIG_EIDAS_LEVEL));
    }

    public void setEidasLevel(final EIDAS eidasLevel) {
        getConfig().put(CONFIG_EIDAS_LEVEL, eidasLevel.name());
    }

    public boolean isEidasOverride() {
        return Boolean.valueOf(getConfig().get(CONFIG_EIDAS_OVERRIDE));
    }

    public void setEidasOverride(final boolean eidasOverride) {
        getConfig().put(CONFIG_EIDAS_OVERRIDE, Boolean.toString(eidasOverride));
    }

    public Environment getEnvironment() {
        return Environment.valueOf(getConfig().get(CONFIG_ENVIRONMENT));
    }

    public void setEnvironment(final Environment environment) {
        getConfig().put(CONFIG_ENVIRONMENT, environment.name());
    }

}
