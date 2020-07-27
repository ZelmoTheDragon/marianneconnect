package com.github.zelmothedragon.marianneconnect.keycloak.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Lieu de naissance (France uniquement). <b>Attention cette fonctionnalité peut
 * être supprimée par Keycloak dans les prochaines versions.</b>
 *
 * @author MOSELLE Maxime
 */
@Entity
@Table(name = "marianneconnect_birth_place")
public class BirthPlace implements Serializable {

    /**
     * Clef primaire.
     */
    @Id
    @Column(name = "id", unique = true, columnDefinition = UUIDConverter.COLUMN_DEFINITION)
    private UUID id;

    /**
     * Code INSEE de la ville.
     */
    @Column(name = "insee_code", nullable = false, length = 5)
    private String inseeCode;

    /**
     * Code postal de la ville.
     */
    @Column(name = "postal_code", nullable = false, length = 5)
    private String postalCode;

    /**
     * Nom de la ville.
     */
    @Column(name = "town_name", nullable = false, length = 255)
    private String townName;

    /**
     * Constructeur par défaut. Requis pour le fonctionnement de Keycloak.
     */
    public BirthPlace() {
        // RAS
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, inseeCode, postalCode, townName);
    }

    @Override
    public boolean equals(Object obj) {
        final boolean eq;
        if (this == obj) {
            eq = true;
        } else if (!(this instanceof BirthPlace)) {
            eq = false;
        } else {
            var other = (BirthPlace) obj;
            eq = Objects.equals(id, other.id)
                    && Objects.equals(inseeCode, other.inseeCode)
                    && Objects.equals(postalCode, other.postalCode)
                    && Objects.equals(townName, other.townName);
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

    public String getInseeCode() {
        return inseeCode;
    }

    public void setInseeCode(String inseeCode) {
        this.inseeCode = inseeCode;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

}
