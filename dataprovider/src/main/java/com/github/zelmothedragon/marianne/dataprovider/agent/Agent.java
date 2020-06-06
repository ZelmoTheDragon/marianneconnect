package com.github.zelmothedragon.marianne.dataprovider.agent;

import java.util.Objects;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbPropertyOrder;
import javax.json.bind.config.PropertyOrderStrategy;

/**
 * Agent.
 *
 * @author MOSELLE Maxime
 */
@JsonbPropertyOrder(PropertyOrderStrategy.LEXICOGRAPHICAL)
public class Agent {

    /**
     * Adresse de courriel.
     */
    @JsonbProperty(value = "email", nillable = false)
    private String email;

    /**
     * Prénom.
     */
    @JsonbProperty(value = "givenName", nillable = false)
    private String givenName;

    /**
     * Nom de famille.
     */
    @JsonbProperty(value = "familyName", nillable = false)
    private String familyName;

    /**
     * Constructeur par défaut. Requis pour le fonctionnement des technologies
     * de Java EE.
     */
    public Agent() {
        // RAS
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, givenName, familyName);
    }

    @Override
    public boolean equals(Object obj) {
        final boolean eq;
        if (this == obj) {
            eq = true;
        } else if (!(obj instanceof Agent)) {
            eq = false;
        } else {
            Agent other = (Agent) obj;
            eq = Objects.equals(email, other.email)
                    && Objects.equals(givenName, other.givenName)
                    && Objects.equals(familyName, other.familyName);
        }
        return eq;
    }

    @Override
    public String toString() {
        return JsonbBuilder
                .create()
                .toJson(this);
    }

    // ------------------------------
    // Accesseurs & Muttateurs
    // ------------------------------
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

}
