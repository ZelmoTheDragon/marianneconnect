package fr.moselleacademy.marianne.dataprovider.security;

import java.util.Objects;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbPropertyOrder;
import javax.json.bind.annotation.JsonbTransient;
import javax.json.bind.config.PropertyOrderStrategy;

/**
 * Identifiant de connexion applicatif.
 *
 * @author MOSELLE Maxime
 */
@JsonbPropertyOrder(PropertyOrderStrategy.LEXICOGRAPHICAL)
public class Credentials {

    /**
     * Identifiant.
     */
    @JsonbProperty(value = "clientId", nillable = false)
    private String clientId;

    /**
     * Secret (en clair).
     */
    @JsonbTransient
    private String clientSecret;

    /**
     * Constructeur par défaut. Requis pour le fonctionnement des technologies
     * de Java EE.
     */
    public Credentials() {
        // RAS
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId, clientSecret);
    }

    @Override
    public boolean equals(Object obj) {
        final boolean eq;
        if (this == obj) {
            eq = true;
        } else if (!(obj instanceof Credentials)) {
            eq = false;
        } else {
            Credentials other = (Credentials) obj;
            eq = Objects.equals(clientId, other.clientId)
                    && Objects.equals(clientSecret, other.clientSecret);
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
    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

}
