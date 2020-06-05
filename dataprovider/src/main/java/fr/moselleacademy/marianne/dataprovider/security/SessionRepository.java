package fr.moselleacademy.marianne.dataprovider.security;

import java.util.HashSet;
import java.util.Set;
import javax.enterprise.context.ApplicationScoped;

/**
 * Session applicative.
 *
 * @author MOSELLE Maxime
 */
@ApplicationScoped
public class SessionRepository {

    /**
     * Sessions connectées.
     */
    private final Set<Token> session;

    public SessionRepository() {
        this.session = new HashSet<>();
    }

    /**
     * Stocker en session un jeton.
     *
     * @param token Un jeton
     */
    public void add(final Token token) {
        session.add(token);
    }

}
