package fr.moselleacademy.marianne.mapper;

import fr.moselleacademy.marianne.social.FranceConnectIdentityProviderFactory;
import org.keycloak.broker.oidc.mappers.UserAttributeMapper;

/**
 *
 * @author MOSELLE Maxime
 */
public class FranceConnectUserAttributeMapper extends UserAttributeMapper {

    public static final String ID = "franceconnect-particulier-user-attribute-mapper";

    public static final String[] PROVIDERS = {FranceConnectIdentityProviderFactory.ID};

    public FranceConnectUserAttributeMapper() {
    }

    @Override
    public String getId() {
        return ID;
    }

    @Override
    public String[] getCompatibleProviders() {
        return PROVIDERS;
    }

}
