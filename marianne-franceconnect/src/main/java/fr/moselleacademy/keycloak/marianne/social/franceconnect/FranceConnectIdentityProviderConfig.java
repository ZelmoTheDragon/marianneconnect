package fr.moselleacademy.keycloak.marianne.social.franceconnect;

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

    public FranceConnectIdentityProviderConfig(IdentityProviderModel model) {
        super(model);

        if (!getConfig().containsKey(CONFIG_EIDAS_LEVEL)) {
            setEidasLevel(EIDAS.LEVEL_3);
        }
        if (!getConfig().containsKey(CONFIG_EIDAS_OVERRIDE)) {
            setEidasOverride(true);
        }
    }

    public EIDAS getEidasLevel() {
        return EIDAS.fromString(getConfig().get(CONFIG_EIDAS_LEVEL));
    }

    public void setEidasLevel(final EIDAS eidasLevel) {
        getConfig().put(CONFIG_EIDAS_LEVEL, eidasLevel.getValue());
    }

    public boolean isEidasOverride() {
        return Boolean.valueOf(getConfig().get(CONFIG_EIDAS_OVERRIDE));
    }

    public void setEidasOverride(final boolean eidasOverride) {
        getConfig().put(CONFIG_EIDAS_OVERRIDE, Boolean.toString(eidasOverride));
    }

}
