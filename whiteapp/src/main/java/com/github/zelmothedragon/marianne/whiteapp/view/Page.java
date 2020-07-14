package com.github.zelmothedragon.marianne.whiteapp.view;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Enumération des pages de l'application.
 *
 * @author MOSELLE Maxime
 */
public enum Page {

    INDEX("/index");

    private static final Logger LOGGER = Logger.getLogger(Page.class.getName());

    /**
     * L'extension des pages.
     */
    public static final String EXTENSION = ".xhtml";

    /**
     * Redirection de page JSF.
     */
    private static final String REDIRECT = "?faces-redirect=true";

    /**
     * Le chemin.
     */
    private final String path;

    private Page(String path) {
        this.path = path;
    }

    public String redirect() {
        return path + REDIRECT;
    }

    public String toPath() {
        return path;
    }

    public String toRelativeURL() {
        return path + EXTENSION;
    }

    /**
     * Redirection serveur pour controller bean et servlet.
     *
     * @param req Requête HTTP
     * @param res Réponse HTTP
     */
    public void callRedirect(
            final HttpServletRequest req,
            final HttpServletResponse res) {

        try {
            res.setStatus(HttpURLConnection.HTTP_MOVED_TEMP);
            res.sendRedirect(toAbsoluteURL(req));
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "redirection failed for the path " + path, ex);
        }
    }

    /**
     * Permet de récupérer l'adresse complète d'une page.
     *
     * @param req Requête HTTP
     * @return l'adresse complète.
     */
    private String toAbsoluteURL(final HttpServletRequest req) {
        StringBuilder absoluteUrl = new StringBuilder(req.getRequestURL().toString());
        absoluteUrl.setLength(absoluteUrl.indexOf(req.getRequestURI()));
        absoluteUrl.append(req.getContextPath());
        absoluteUrl.append(toRelativeURL());
        return absoluteUrl.toString();
    }

}
