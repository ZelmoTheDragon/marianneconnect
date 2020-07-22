package com.github.zelmothedragon.marianneconnect.keycloak.social;

import org.keycloak.broker.provider.AbstractIdentityProviderFactory;
import org.keycloak.broker.social.SocialIdentityProviderFactory;
import org.keycloak.models.IdentityProviderModel;
import org.keycloak.models.KeycloakSession;

/**
 * Fabrique du fournisseur d'identité FranceConnect. Keycloak s'appuie sur la
 * technique de <i>ServiceLoader</i>, l'implémentation doit être déclarée dans
 * le fichier nommé
 * <i>org.keycloak.broker.social.SocialIdentityProviderFactory</i> du répertoire
 * <i>META-INF/services</i>.
 *
 * @author MOSELLE Maxime
 */
public final class FranceConnectIdentityProviderFactory extends AbstractIdentityProviderFactory<FranceConnectIdentityProvider>
        implements SocialIdentityProviderFactory<FranceConnectIdentityProvider> {

    /**
     * Identifiant technique.
     */
    public static final String ID = "franceconnect-particulier";

    /**
     * Nom.
     */
    public static final String NAME = "FranceConnect Particulier";

    /**
     * Constructeur par défaut. Requis pour le fonctionnement de Keycloak.
     */
    public FranceConnectIdentityProviderFactory() {
        // RAS
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
    public FranceConnectIdentityProviderConfig createConfig() {
        // Forcer le transtypage
        return new FranceConnectIdentityProviderConfig();
    }

    // ------------------------------
    // Accesseurs
    // ------------------------------
    @Override
    public String getId() {
        return ID;
    }

    @Override
    public String getName() {
        return NAME;
    }

}
