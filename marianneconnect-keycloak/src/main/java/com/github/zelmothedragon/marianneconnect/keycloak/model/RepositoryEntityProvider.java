package com.github.zelmothedragon.marianneconnect.keycloak.model;

import java.util.List;
import org.keycloak.connections.jpa.entityprovider.JpaEntityProvider;
import org.keycloak.models.KeycloakSession;

/**
 * Gestionnaire des entités persistantes. <b>Attention cette fonctionnalité peut
 * être supprimée par Keycloak dans les prochaines versions.</b>
 *
 * @author MOSELLE Maxime
 */
public class RepositoryEntityProvider implements JpaEntityProvider {

    /**
     * Session Keycloak.
     */
    private final KeycloakSession session;

    /**
     * Constructeur restreint.
     *
     * @param session Session Keycloak
     */
    RepositoryEntityProvider(KeycloakSession session) {
        this.session = session;
    }

    @Override
    public List<Class<?>> getEntities() {
        return List.of(Gender.class, BirthCountry.class, BirthPlace.class);
    }

    @Override
    public String getChangelogLocation() {
        return "META-INF/changelog-marianneconnect.xml";
    }

    @Override
    public String getFactoryId() {
        return RepositoryEntityProviderFactory.ID;
    }

    @Override
    public void close() {
        // RAS
    }

}
