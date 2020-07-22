package com.github.zelmothedragon.marianneconnect.keycloak.mapper;

import com.github.zelmothedragon.marianneconnect.keycloak.social.FranceConnectIdentityProviderFactory;
import org.keycloak.broker.oidc.mappers.UsernameTemplateMapper;

/**
 * Liaison du nom d'utilisateur de l'identité numérique pour le fournisseur
 * d'identité FranceConnect. Keycloak s'appuie sur la technique de
 * <i>ServiceLoader</i>, l'implémentation doit être déclarée dans le fichier
 * nommé
 * <i>org.keycloak.broker.provider.IdentityProviderMapper</i> du répertoire
 * <i>META-INF/services</i>.
 *
 * @author MOSELLE Maxime
 */
public final class FranceConnectUsernameTemplateMapper extends UsernameTemplateMapper {

    /**
     * Identifiant technique.
     */
    public static final String ID = "franceconnect-particulier-username-template-mapper";

    /**
     * Fournisseurs compatibles.
     */
    public static final String[] PROVIDERS = {FranceConnectIdentityProviderFactory.ID};

    /**
     * Constructeur par défaut. Requis pour le fonctionnement de Keycloak.
     */
    public FranceConnectUsernameTemplateMapper() {
        // RAS
    }

    // ------------------------------
    // Accesseurs
    // ------------------------------
    @Override
    public String getId() {
        return ID;
    }

    @Override
    public String[] getCompatibleProviders() {
        return PROVIDERS;
    }

}
