package fr.moselleacademy.marianne.dataprovider.security;

import fr.moselleacademy.marianne.dataprovider.util.CSV;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

/**
 * Entrepôt de données.
 *
 * @author MOSELLE Maxime
 */
@ApplicationScoped
public class SessionRepository {

    /**
     * Journalisation.
     */
    private static final Logger LOGGER = Logger.getLogger(SessionRepository.class.getName());

    /**
     * Chemin du fichier de données.
     */
    private static final String DATABASE_FILE = "/credentials.csv";

    /**
     * Entête du fichier CSV.
     */
    private static final String CSV_HEADER = "clientId, clientSecret";

    /**
     * Séparateur de cellule.
     */
    private static final String CSV_SEPARATOR = ",";

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
     * Sessions connectées.
     */
    private final Set<Token> session;

    public SessionRepository() {
        this.data = new HashSet<>();
        this.session = new HashSet<>();
    }

    /**
     * Peupler les données.
     */
    @PostConstruct
    protected void populateData() {
        List<Credentials> entities = CSV.readFile(
                DATABASE_FILE,
                CSV_HEADER,
                SessionRepository::fromCSV
        );

        data.clear();
        data.addAll(entities);
    }

    public Optional<Credentials> findByCredentials(final String clientId, final String clientSecret) {
        return data
                .stream()
                .filter(e -> Objects.equals(e.getClientId(), clientId))
                .filter(e -> Objects.equals(e.getClientSecret(), clientSecret))
                .findFirst();
    }

    public void storeInSession(final Token token) {
        session.add(token);
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
