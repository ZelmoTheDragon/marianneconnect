package fr.moselleacademy.marianne.dataprovider.security;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

/**
 * Session applicative.
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
     * Sessions connectées.
     */
    private final Set<Token> session;

    /**
     * Service de revoquation de jeton.
     */
    private ScheduledExecutorService timer;

    /**
     * Constructeur par défaut. Requis pour le fonctionnement des technologies
     * de Java EE.
     */
    public SessionRepository() {
        this.session = new HashSet<>();
    }

    @PostConstruct
    protected void init() {
        timer = Executors.newScheduledThreadPool(1);
        timer.scheduleAtFixedRate(this::revokeTokenIfExpired, 0, 60, TimeUnit.SECONDS);
    }

    /**
     * Stocker en session un jeton.
     *
     * @param token Un jeton
     */
    public void add(final Token token) {
        session.add(token);
    }

    /**
     * Supprimer un jeton.
     *
     * @param token Un jeton
     */
    public void remove(final Token token) {
        LOGGER.fine(() -> "Removing token: " + token);
        session.remove(token);
    }

    /**
     * Rechercher un jeton existant.
     *
     * @param refreshToken Jeton de rafraichissement
     * @return Une option contenant ou non le jeton de session
     */
    public Optional<Token> findByRefreshToken(final String refreshToken) {
        return session
                .stream()
                .filter(e -> Objects.equals(refreshToken, e.getRefreshToken().toString()))
                .findFirst();
    }
    
    /**
     * Rechercher un jeton existant.
     *
     * @param accessToken Jeton d'access
     * @return Une option contenant ou non le jeton de session
     */
    public Optional<Token> findByAccessToken(final String accessToken) {
        return session
                .stream()
                .filter(e -> Objects.equals(accessToken, e.getAccessToken().toString()))
                .findFirst();
    }

    /**
     * Révoquer les jetons expirés.
     */
    private void revokeTokenIfExpired() {
        LOGGER.fine(() -> "Looking for expired token");
        session
                .stream()
                .filter(e -> isExpired(e.getIssuedAt()))
                .forEach(this::remove);
    }

    /**
     * Vérifier qu'un date est dans le passé
     *
     * @param date Date à vérifier, on ajoute 30 minutes pour le délai
     * d'expiration
     * @return La valeur <code>true</code> si la date est dans la passé, sinon
     * la valeur <code>false</code> est retournée
     */
    private static boolean isExpired(final LocalDateTime date) {
        return LocalDateTime
                .now()
                .isAfter(date.plus(30, ChronoUnit.MINUTES));
    }

}
