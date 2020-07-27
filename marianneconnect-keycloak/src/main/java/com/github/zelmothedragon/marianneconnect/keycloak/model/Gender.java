package com.github.zelmothedragon.marianneconnect.keycloak.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Genre. <b>Attention cette fonctionnalité peut être supprimée par Keycloak
 * dans les prochaines versions.</b>
 *
 * @author MOSELLE Maxime
 */
@Entity
@Table(name = "marianneconnect_gender")
public class Gender implements Serializable {

    /**
     * Clef primaire.
     */
    @Id
    @Column(name = "id", unique = true, columnDefinition = UUIDConverter.COLUMN_DEFINITION)
    private UUID id;

    /**
     * Nom du genre selon la norme OpenID Connect.
     */
    @Column(name = "name", nullable = false, unique = true, length = 255)
    private String name;

    /**
     * Description du genre.
     */
    @Column(name = "description", length = 255)
    private String description;

    /**
     * Constructeur par défaut. Requis pour le fonctionnement de Keycloak.
     */
    public Gender() {
        // RAS
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description);
    }

    @Override
    public boolean equals(Object obj) {
        final boolean eq;
        if (this == obj) {
            eq = true;
        } else if (!(this instanceof Gender)) {
            eq = false;
        } else {
            var other = (Gender) obj;
            eq = Objects.equals(id, other.id)
                    && Objects.equals(name, other.name)
                    && Objects.equals(description, other.description);
        }
        return eq;
    }

    // ------------------------------
    // Accesseurs & Muttateurs
    // ------------------------------
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
