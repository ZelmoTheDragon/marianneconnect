package com.github.zelmothedragon.marianneconnect.dataprovider.security;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.annotation.JsonbDateFormat;
import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbPropertyOrder;
import javax.json.bind.config.PropertyOrderStrategy;

/**
 * Jeton de session.
 *
 * @author MOSELLE Maxime
 */
@JsonbPropertyOrder(PropertyOrderStrategy.LEXICOGRAPHICAL)
public class Token {

    /**
     * Jeton d'accès.
     */
    @JsonbProperty(value = "accessToken", nillable = false)
    private UUID accessToken;

    /**
     * Jeton de rafraichissement.
     */
    @JsonbProperty(value = "refreshToken", nillable = false)
    private UUID refreshToken;

    /**
     * Date de génération du jeton.
     */
    @JsonbDateFormat(value = JsonbDateFormat.TIME_IN_MILLIS, locale = "fr")
    @JsonbProperty(value = "issuedAt", nillable = false)
    private LocalDateTime issuedAt;

    /**
     * Charge utile.
     */
    @JsonbProperty(value = "credentials", nillable = false)
    private Credentials credentials;

    /**
     * Constructeur par défaut. Requis pour le fonctionnement des technologies
     * de Java EE.
     */
    public Token() {
        // RAS
    }

    /**
     * Générer un nouveau Jeton.
     *
     * @param credentials Données métier
     * @return Un nouveau jeton
     */
    public static Token generate(final Credentials credentials) {
        Token entity = new Token();
        entity.setAccessToken(UUID.randomUUID());
        entity.setRefreshToken(UUID.randomUUID());
        entity.setIssuedAt(LocalDateTime.now());
        entity.setCredentials(credentials);
        return entity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                accessToken,
                refreshToken,
                issuedAt,
                credentials
        );
    }

    @Override
    public boolean equals(Object obj) {
        final boolean eq;
        if (this == obj) {
            eq = true;
        } else if (!(obj instanceof Token)) {
            eq = false;
        } else {
            Token other = (Token) obj;
            eq = Objects.equals(accessToken, other.accessToken)
                    && Objects.equals(refreshToken, other.refreshToken)
                    && Objects.equals(issuedAt, other.issuedAt)
                    && Objects.equals(credentials, other.credentials);
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
    public UUID getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(UUID accessToken) {
        this.accessToken = accessToken;
    }

    public UUID getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(UUID refreshToken) {
        this.refreshToken = refreshToken;
    }

    public LocalDateTime getIssuedAt() {
        return issuedAt;
    }

    public void setIssuedAt(LocalDateTime issuedAt) {
        this.issuedAt = issuedAt;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

}
