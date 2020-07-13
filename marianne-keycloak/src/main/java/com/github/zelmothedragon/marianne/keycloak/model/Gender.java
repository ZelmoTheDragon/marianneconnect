package com.github.zelmothedragon.marianne.keycloak.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author MOSELLE Maxime
 */
@Deprecated
@Entity
@Table(name = "gender")
public class Gender implements Serializable {

    /**
     * Clef primaire.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

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
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
