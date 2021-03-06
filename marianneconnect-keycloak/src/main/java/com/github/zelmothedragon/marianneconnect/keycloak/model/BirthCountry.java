package com.github.zelmothedragon.marianneconnect.keycloak.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Pays de naissance. <b>Attention cette fonctionnalité peut être supprimée par
 * Keycloak dans les prochaines versions.</b>
 *
 * @author MOSELLE Maxime
 */
@Entity
@Table(name = "marianneconnect_birth_country")
public class BirthCountry implements Serializable {

    /**
     * Clef primaire.
     */
    @Id
    @Column(name = "id", unique = true, columnDefinition = UUIDConverter.COLUMN_DEFINITION)
    private UUID id;

    /**
     * Code INSEE du pays.
     */
    @Column(name = "insee_code", nullable = false, length = 5)
    private String inseeCode;

    /**
     * Nom du pays.
     */
    @Column(name = "country_name", nullable = false, length = 255)
    private String countryName;

    /**
     * Constructeur par défaut. Requis pour le fonctionnement de Keycloak.
     */
    public BirthCountry() {
        // RAS
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, inseeCode, countryName);
    }

    @Override
    public boolean equals(Object obj) {
        final boolean eq;
        if (this == obj) {
            eq = true;
        } else if (!(this instanceof BirthCountry)) {
            eq = false;
        } else {
            var other = (BirthCountry) obj;
            eq = Objects.equals(id, other.id)
                    && Objects.equals(inseeCode, other.inseeCode)
                    && Objects.equals(countryName, other.countryName);
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

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

}
