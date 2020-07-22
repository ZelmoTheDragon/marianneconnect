package com.github.zelmothedragon.marianneconnect.keycloak.protocol;

import com.github.zelmothedragon.marianneconnect.keycloak.util.Messages;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.keycloak.provider.ProviderConfigProperty;
import org.keycloak.provider.ProviderConfigurationBuilder;

/**
 * Configuration du fournisseur de données externe.
 *
 * @author MOSELLE Maxime
 */
final class ExternalClaimConfig {

    /**
     * Configuration pour l'dentifiant de connexion.
     */
    static final String CLIENT_ID = "dataprovider.clientId";

    /**
     * Configuration pour le mot de passe (en clair).
     */
    static final String CLIENT_SECRET = "dataprovider.clientSecret";

    /**
     * Configuration pour le clef secrète.
     */
    static final String KEY = "dataprovider.key";

    /**
     * Configuration pour l'URL d'autorisation.
     */
    static final String TOKEN_URL = "dataprovider.tokenUrl";

    /**
     * Configuration pour l'URL de récupération des données.
     */
    static final String CLAIMS_URL = "dataprovider.claimsUrl";

    /**
     * Nom de l'attribut du jeton de données source à récupérer.
     */
    static final String CLAIMS_SOURCE_NAME = "dataprovider.claimsSourceName";

    /**
     * Nouveau nom de l'attribut du jeton d'identité à fournir.
     */
    static final String CLAIMS_TARGET_NAME = "dataprovider.claimsTargetName";

    /**
     * Configuration de Keycloak pour le fournisseur de données externe.
     */
    private final Map<String, String> configuration;

    /**
     * Constructeur à visibilité restreinte. Construit la configuration du
     * fournisseur de données externe.
     */
    ExternalClaimConfig() {
        this.configuration = new HashMap<>();
    }

    /**
     * Construire la liste des champs de configuration du fournisseur de données
     * externe.
     *
     * @return La liste des champs de configuration
     */
    List<ProviderConfigProperty> createConfiguration() {
        ProviderConfigProperty p0 = new ProviderConfigProperty(
                CLIENT_ID,
                Messages.get("dataprovider.client_id.label"),
                Messages.get("dataprovider.client_id.help"),
                ProviderConfigProperty.STRING_TYPE,
                null
        );

        ProviderConfigProperty p1 = new ProviderConfigProperty(
                CLIENT_SECRET,
                Messages.get("dataprovider.client_secret.label"),
                Messages.get("dataprovider.client_secret.help"),
                ProviderConfigProperty.STRING_TYPE,
                null,
                true
        );

        ProviderConfigProperty p2 = new ProviderConfigProperty(
                KEY,
                Messages.get("dataprovider.key.label"),
                Messages.get("dataprovider.key.help"),
                ProviderConfigProperty.STRING_TYPE,
                null,
                true
        );

        ProviderConfigProperty p3 = new ProviderConfigProperty(
                TOKEN_URL,
                Messages.get("dataprovider.token_url.label"),
                Messages.get("dataprovider.token_url.help"),
                ProviderConfigProperty.STRING_TYPE,
                null
        );

        ProviderConfigProperty p4 = new ProviderConfigProperty(
                CLAIMS_URL,
                Messages.get("dataprovider.claims_url.label"),
                Messages.get("dataprovider.claims_url.help"),
                ProviderConfigProperty.STRING_TYPE,
                null
        );

        ProviderConfigProperty p5 = new ProviderConfigProperty(
                CLAIMS_SOURCE_NAME,
                Messages.get("dataprovider.claims_source_name.label"),
                Messages.get("dataprovider.claims_source_name.help"),
                ProviderConfigProperty.STRING_TYPE,
                null
        );

        ProviderConfigProperty p6 = new ProviderConfigProperty(
                CLAIMS_TARGET_NAME,
                Messages.get("dataprovider.claims_target_name.label"),
                Messages.get("dataprovider.claims_target_name.help"),
                ProviderConfigProperty.STRING_TYPE,
                null
        );

        return ProviderConfigurationBuilder
                .create()
                .property(p0)
                .property(p1)
                .property(p2)
                .property(p3)
                .property(p4)
                .property(p5)
                .property(p6)
                .build();
    }

    // ------------------------------
    // Accesseurs & Muttateurs
    // ------------------------------
    void setConfiguration(final Map<String, String> config) {
        configuration.clear();
        configuration.putAll(config);
    }

    String getClientId() {
        return configuration.get(CLIENT_ID);
    }

    String getClientSecret() {
        return configuration.get(CLIENT_SECRET);
    }

    String getKey() {
        return configuration.get(KEY);
    }

    String getTokenUrl() {
        return configuration.get(TOKEN_URL);
    }

    String getClaimsUrl() {
        return configuration.get(CLAIMS_URL);
    }

    String getClaimsSourceName() {
        return configuration.get(CLAIMS_SOURCE_NAME);
    }

    String getClaimsTargetName() {
        return configuration.get(CLAIMS_TARGET_NAME);
    }

}
