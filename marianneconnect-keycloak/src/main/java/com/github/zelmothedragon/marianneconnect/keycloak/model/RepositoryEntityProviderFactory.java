package com.github.zelmothedragon.marianneconnect.keycloak.model;

import org.keycloak.Config;
import org.keycloak.connections.jpa.entityprovider.JpaEntityProviderFactory;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;

/**
 * Fabrique du gestionnaire d'entité persistante. <b>Attention cette
 * fonctionnalité peut être supprimée par Keycloak dans les prochaines
 * versions.</b>
 *
 * @author MOSELLE Maxime
 */
public class RepositoryEntityProviderFactory implements JpaEntityProviderFactory {

    /**
     * Identifiant technique.
     */
    public static final String ID = "repository-entity-provider";

    /**
     * Constructeur par défaut. Requis pour le fonctionnement de Keycloak.
     */
    public RepositoryEntityProviderFactory() {
        // rAS
    }

    @Override
    public RepositoryEntityProvider create(final KeycloakSession session) {
        return new RepositoryEntityProvider(session);
    }

    @Override
    public void init(final Config.Scope config) {
        // RAS
    }

    @Override
    public void postInit(final KeycloakSessionFactory factory) {
        // RAS
    }

    @Override
    public void close() {
        // RAS
    }

    @Override
    public String getId() {
        return ID;
    }

}
