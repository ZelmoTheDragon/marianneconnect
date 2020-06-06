package com.github.zelmothedragon.marianne.keycloak.social;

import org.keycloak.broker.oidc.OIDCIdentityProviderConfig;
import org.keycloak.models.IdentityProviderModel;

/**
 *
 * @author MOSELLE Maxime
 */
public final class FranceConnectIdentityProviderConfig extends OIDCIdentityProviderConfig {

    private static final long serialVersionUID = 1L;

    public static final String CONFIG_EIDAS_LEVEL = "eidasLevel";

    public static final String CONFIG_EIDAS_OVERRIDE = "eidasOverride";

    public static final String CONFIG_ENVIRONMENT = "environment";

    public FranceConnectIdentityProviderConfig() {
        this(null);
    }

    public FranceConnectIdentityProviderConfig(final IdentityProviderModel model) {
        super(model);

        if (!getConfig().containsKey(CONFIG_EIDAS_LEVEL)) {
            setEidasLevel(EIDAS.LEVEL_3);
        }
        if (!getConfig().containsKey(CONFIG_EIDAS_OVERRIDE)) {
            setEidasOverride(true);
        }
        if (!getConfig().containsKey(CONFIG_ENVIRONMENT)) {
            setEnvironment(Environment.INTEGRATION);
        }
        
        var env = getEnvironment();
        setAuthorizationUrl(env.getAutorizationURL());
        setTokenUrl(env.getTokenURL());
        setUserInfoUrl(env.getUserInfoURL());
        setLogoutUrl(env.getUserInfoURL());
    }

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
