package fr.moselleacademy.keycloak.marianne.social.franceconnect;

import org.keycloak.broker.provider.AbstractIdentityProviderFactory;
import org.keycloak.broker.social.SocialIdentityProviderFactory;
import org.keycloak.models.IdentityProviderModel;
import org.keycloak.models.KeycloakSession;

/**
 *
 * @author MOSELLE Maxime
 */
public final class FranceConnectIdentityProviderFactory extends AbstractIdentityProviderFactory<FranceConnectIdentityProvider>
        implements SocialIdentityProviderFactory<FranceConnectIdentityProvider> {

    public static final String ID = "franceconnect-particulier";

    public static final String NAME = "FranceConnect Particulier";

    public FranceConnectIdentityProviderFactory() {
    }

    @Override
    public FranceConnectIdentityProvider create(
            final KeycloakSession session,
            final IdentityProviderModel model) {

        return new FranceConnectIdentityProvider(
                session,
                new FranceConnectIdentityProviderConfig(model)
        );
    }

    @Override
    public <C extends IdentityProviderModel> C createConfig() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getId() {
        return ID;
    }

    @Override
    public String getName() {
        return NAME;
    }

}
