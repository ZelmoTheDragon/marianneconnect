package com.github.zelmothedragon.marianne.keycloak.protocol;

import com.github.zelmothedragon.marianne.keycloak.util.Messages;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.keycloak.provider.ProviderConfigProperty;
import org.keycloak.provider.ProviderConfigurationBuilder;

/**
 *
 * @author MOSELLE Maxime
 */
public final class ExternalClaimConfig {

    public static final String CLIENT_ID = "dataprovider.clientId";

    public static final String CLIENT_SECRET = "dataprovider.clientSecret";

    public static final String KEY = "dataprovider.key";

    public static final String TOKEN_URL = "dataprovider.tokenUrl";

    public static final String CLAIMS_URL = "dataprovider.claimsUrl";

    public static final String CLAIMS_SOURCE_NAME = "dataprovider.claimsSourceName";

    public static final String CLAIMS_TARGET_NAME = "dataprovider.claimsTargetName";

    private final Map<String, String> configuration;

    public ExternalClaimConfig() {
        this.configuration = new HashMap<>();
    }

    public List<ProviderConfigProperty> createConfiguration() {
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
                Messages.get("dataprovider.claims__source_name.help"),
                ProviderConfigProperty.STRING_TYPE,
                null
        );

        ProviderConfigProperty p6 = new ProviderConfigProperty(
                CLAIMS_TARGET_NAME,
                Messages.get("dataprovider.claims_target_name.label"),
                Messages.get("dataprovider.claims__target_name.help"),
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

    public void loadConfiguration(final Map<String, String> config) {
        configuration.clear();
        configuration.putAll(config);
    }

    public String getClientId() {
        return configuration.get(CLIENT_ID);
    }

    public String getClientSecret() {
        return configuration.get(CLIENT_SECRET);
    }

    public String getKey() {
        return configuration.get(KEY);
    }

    public String getTokenUrl() {
        return configuration.get(TOKEN_URL);
    }

    public String getClaimsUrl() {
        return configuration.get(CLAIMS_URL);
    }

    public String getClaimsSourceName() {
        return configuration.get(CLAIMS_SOURCE_NAME);
    }

    public String getClaimsTargetName() {
        return configuration.get(CLAIMS_TARGET_NAME);
    }

}
