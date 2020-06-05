package fr.moselleacademy.marianne.dataprovider.security;

import fr.moselleacademy.marianne.dataprovider.util.CSV;
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
public class CredentialsRepository {

    /**
     * Chemin du fichier de données.
     */
    private static final String DATABASE_FILE = "/credentials.csv";

    /**
     * Entête du fichier CSV.
     */
    private static final String CSV_HEADER = "clientId, clientSecret";

    /**
     * Colonne "clientId".
     */
    private static final int COLUMN_CLIENT_ID = 0;

    /**
     * Colonne "clientSecret".
     */
    private static final int COLUMN_CLIENT_SECRET = 1;

    /**
     * Base de données en mémoire.
     */
    private final Set<Credentials> data;

    /**
     * Constructeur par défaut. Requis pour le fonctionnement des technologies
     * de Java EE.
     */
    public CredentialsRepository() {
        this.data = new HashSet<>();
    }

    /**
     * Peupler les données.
     */
    @PostConstruct
    protected void populateData() {
        List<Credentials> entities = CSV.readFile(DATABASE_FILE,
                CSV_HEADER,
                CredentialsRepository::fromCSV
        );

        data.clear();
        data.addAll(entities);
    }

    /**
     * Rechercher un idenfiant.
     *
     * @param clientId Identifiant de connexion
     * @param clientSecret Mot de passe (en clair)
     * @return Une option contenant ou non l'identifiant
     */
    public Optional<Credentials> findByCredentials(final String clientId, final String clientSecret) {
        return data
                .stream()
                .filter(e -> Objects.equals(e.getClientId(), clientId))
                .filter(e -> Objects.equals(e.getClientSecret(), clientSecret))
                .findFirst();
    }

    /**
     * Convertir une ligne CSV en identifiant.
     *
     * @param line Line du fichier CSV
     * @return Un identidiant peupler avec la ligne CSV
     */
    private static Credentials fromCSV(final String[] line) {
        Credentials entity = new Credentials();
        entity.setClientId(line[COLUMN_CLIENT_ID].trim());
        entity.setClientSecret(line[COLUMN_CLIENT_SECRET].trim());
        return entity;
    }

}
