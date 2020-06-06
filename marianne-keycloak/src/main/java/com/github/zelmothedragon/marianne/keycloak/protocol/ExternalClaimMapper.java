package com.github.zelmothedragon.marianne.keycloak.protocol;

import com.github.zelmothedragon.marianne.keycloak.util.Messages;
import java.util.List;
import org.keycloak.models.ClientSessionContext;
import org.keycloak.models.KeycloakSession;
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
 * données externe (bouchon). Keycloak s'appuie sur la technique de
 * <i>ServiceLoader</i>, l'implémentation doit être déclarée dans le fichier
 * nommé
 * <i>org.keycloak.protocol.ProtocolMapper</i> du répertoire
 * <i>META-INF/services</i>.
 *
 * @author MOSELLE Maxime
 */
public class ExternalClaimMapper extends AbstractOIDCProtocolMapper
        implements OIDCAccessTokenMapper, OIDCIDTokenMapper, UserInfoTokenMapper {

    /**
     * Identifiant technique.
     */
    public static final String ID = "external-dataprovider";

    /**
     * Configuration.
     */
    private final ExternalClaimConfig config;

    /**
     * Fournisseur de données externe.
     */
    private final ExternalDataProvider provider;

    /**
     * Constructeur par défaut. Requis pour le fonctionnement de Keycloak.
     */
    public ExternalClaimMapper() {
        this.config = new ExternalClaimConfig();
        this.provider = new ExternalDataProvider(config);
    }

    // ------------------------------
    // Accesseurs & Muttateurs
    // ------------------------------
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
        // Permet de construire l'interface web d'administration 
        return config.createConfiguration();
    }

    @Override
    protected void setClaim(
            final IDToken token,
            final ProtocolMapperModel mappingModel,
            final UserSessionModel userSession,
            final KeycloakSession keycloakSession,
            final ClientSessionContext clientSessionCtx) {

        // Ajouter les données complémentaire
        // dans le jeton de l'identité numérique
        
        config.setConfiguration(mappingModel.getConfig());
        String externalClaims = provider.getExternalClaims();
        OIDCAttributeMapperHelper.mapClaim(token, mappingModel, externalClaims);
    }

}
