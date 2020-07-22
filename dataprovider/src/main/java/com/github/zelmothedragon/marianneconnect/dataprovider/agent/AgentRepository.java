package com.github.zelmothedragon.marianneconnect.dataprovider.agent;

import com.github.zelmothedragon.marianneconnect.dataprovider.util.CSV;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

/**
 * Entrepôt de données.
 *
 * @author MOSELLE Maxime
 */
@ApplicationScoped
public class AgentRepository {

    /**
     * Chemin du fichier de données.
     */
    private static final String DATABASE_FILE = "/agents.csv";

    /**
     * Entête du fichier CSV.
     */
    private static final String CSV_HEADER = "email, givenName, familyName";

    /**
     * Colonne "email".
     */
    private static final int COLUMN_EMAIL = 0;

    /**
     * Colonne "givenName".
     */
    private static final int COLUMN_GIVEN_NAME = 1;

    /**
     * Colonne "familyName".
     */
    private static final int COLUMN_FAMILY_NAME = 2;

    /**
     * Base de données en mémoire.
     */
    private final Set<Agent> data;

    /**
     * Constructeur par défaut. Requis pour le fonctionnement des technologies
     * de Java EE.
     */
    public AgentRepository() {
        this.data = new HashSet<>();
    }

    /**
     * Peupler les données.
     */
    @PostConstruct
    protected void populateData() {
        List<Agent> entities = CSV.readFile(
                DATABASE_FILE,
                CSV_HEADER,
                AgentRepository::fromCSV
        );

        data.clear();
        data.addAll(entities);
    }

    /**
     * Rechercher la liste de tous les agents.
     *
     * @return Une liste de tous les agents
     */
    public List<Agent> find() {
        return new ArrayList<>(data);
    }

    /**
     * Rechercher un agent par l'adresse de courriel.
     *
     * @param email Adresse de courriel
     * @return Une option contenant ou non un agent
     */
    public Optional<Agent> findByEmail(final String email) {
        return data
                .stream()
                .filter(e -> Objects.equals(e.getEmail(), email))
                .findFirst();
    }

    /**
     * Convertir une ligne CSV en agent.
     *
     * @param line Line du fichier CSV
     * @return Un agent peupler avec la ligne CSV
     */
    private static Agent fromCSV(final String[] line) {
        Agent entity = new Agent();
        entity.setEmail(line[COLUMN_EMAIL].trim());
        entity.setGivenName(line[COLUMN_GIVEN_NAME].trim());
        entity.setFamilyName(line[COLUMN_FAMILY_NAME].trim());
        return entity;
    }

}
