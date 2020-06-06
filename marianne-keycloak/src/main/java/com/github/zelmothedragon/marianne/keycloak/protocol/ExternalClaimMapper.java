package com.github.zelmothedragon.marianne.keycloak.protocol;

import com.github.zelmothedragon.marianne.keycloak.util.Messages;
import java.util.List;
import org.keycloak.models.ProtocolMapperModel;
import org.keycloak.models.UserSessionModel;
import org.keycloak.protocol.oidc.mappers.AbstractOIDCProtocolMapper;
import org.keycloak.protocol.oidc.mappers.OIDCAccessTokenMapper;
import org.keycloak.protocol.oidc.mappers.OIDCAttributeMapperHelper;
import org.keycloak.protocol.oidc.mappers.OIDCIDTokenMapper;
import org.keycloak.protocol.oidc.mappers.UserInfoTokenMapper;
import org.keycloak.provider.ProviderConfigProperty;
import org.keycloak.representations.IDToken;

/**
 * Ajouter des données complémentaires au jeton en appelant un fournisseur de
 * données externe.
 *
 * @author MOSELLE Maxime
 */
public class ExternalClaimMapper extends AbstractOIDCProtocolMapper
        implements OIDCAccessTokenMapper, OIDCIDTokenMapper, UserInfoTokenMapper {

    public static final String ID = "dataprovider";

    private final ExternalClaimConfig config;

    private final DataProvider provider;

    public ExternalClaimMapper() {
        this.config = new ExternalClaimConfig();
        this.provider = new DataProvider(config);
    }

    @Override
    public String getDisplayCategory() {
        return AbstractOIDCProtocolMapper.TOKEN_MAPPER_CATEGORY;
    }

    @Override
    public String getDisplayType() {
        return Messages.get("dataprovider.display_type");
    }

    @Override
    public String getId() {
        return ID;
    }

    @Override
    public String getHelpText() {
        return Messages.get("dataprovider.help_text");
    }

    @Override
    public List<ProviderConfigProperty> getConfigProperties() {
        return config.createConfiguration();
    }

    @Override
    protected void setClaim(
            final IDToken token,
            final ProtocolMapperModel mappingModel,
            final UserSessionModel userSession) {

        config.loadConfiguration(mappingModel.getConfig());
        String externalClaims = provider.getExternalClaims();
        OIDCAttributeMapperHelper.mapClaim(token, mappingModel, externalClaims);
    }

}
