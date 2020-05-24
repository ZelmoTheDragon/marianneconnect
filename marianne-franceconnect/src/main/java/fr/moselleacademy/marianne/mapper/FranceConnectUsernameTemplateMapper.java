package fr.moselleacademy.marianne.mapper;

import fr.moselleacademy.marianne.social.FranceConnectIdentityProviderFactory;
import org.keycloak.broker.oidc.mappers.UsernameTemplateMapper;

/**
 *
 * @author MOSELLE Maxime
 */
public class FranceConnectUsernameTemplateMapper extends UsernameTemplateMapper {

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
