package com.github.zelmothedragon.marianne.keycloak.mapper;

import com.github.zelmothedragon.marianne.keycloak.social.FranceConnectIdentityProviderFactory;
import org.keycloak.broker.oidc.mappers.UserAttributeMapper;

/**
 *
 * @author MOSELLE Maxime
 */
public final class FranceConnectUserAttributeMapper extends UserAttributeMapper {

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
