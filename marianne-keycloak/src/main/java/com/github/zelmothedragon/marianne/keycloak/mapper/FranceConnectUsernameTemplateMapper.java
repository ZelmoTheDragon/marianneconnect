package com.github.zelmothedragon.marianne.keycloak.mapper;

import com.github.zelmothedragon.marianne.keycloak.social.FranceConnectIdentityProviderFactory;
import org.keycloak.broker.oidc.mappers.UsernameTemplateMapper;

/**
 *
 * @author MOSELLE Maxime
 */
public final class FranceConnectUsernameTemplateMapper extends UsernameTemplateMapper {

    public static final String ID = "franceconnect-particulier-username-template-mapper";

    public static final String[] PROVIDERS = {FranceConnectIdentityProviderFactory.ID};

    public FranceConnectUsernameTemplateMapper() {
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
