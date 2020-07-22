package com.github.zelmothedragon.marianneconnect.dataprovider.agent;

import com.github.zelmothedragon.marianneconnect.dataprovider.util.Hash;
import java.util.List;
import java.util.Objects;
import javax.inject.Inject;
import javax.json.Json;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Point d'entrée de l'API pour la gestion de l'agent.
 *
 * @author MOSELLE Maxime
 */
@Path("/agent")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AgentEndPoint {

    /**
     * Entrepôt de données.
     */
    @Inject
    private AgentRepository repository;

    /**
     * Constructeur par défaut. Requis pour le fonctionnement des technologies de
     * Java EE.
     */
    public AgentEndPoint() {
        // RAS
    }

    /**
     * Obtenir la liste de tous les agents.
     *
     * @return La liste de tous les agents au format JSON
     */
    @GET
    @Path("/list")
    public List<Agent> showAgents() {
        return repository.find();
    }

    /**
     * Obtenir l'identifiant d'un agent en fonction de l'adresse de courriel.
     *
     * @param email Adresse de courriel
     * @return L'identifiant d'un agent si possible
     */
    @GET
    @Path("/identifier")
    public Response getIdentifier(@QueryParam("email") final String email) {
        return repository
                .findByEmail(email)
                .map(Agent::getEmail)
                .map(e -> Hash.execute(Hash.MD5, e))
                .map(e -> Json.createObjectBuilder().add("identifier", e).build())
                .map(e -> Response.ok(e).build())
                .orElseGet(() -> Response.noContent().build());
    }

    /**
     * Obtenir un agent en fonction de l'identifiant.
     *
     * @param identifier Identifiant numérique
     * @return Un agent au format JSON si possible
     */
    @GET
    @Path("/token")
    public Response findByIdentifier(@QueryParam("identifier") final String identifier) {
        return repository
                .find()
                .stream()
                .filter(e -> compare(e.getEmail(), identifier))
                .findFirst()
                .map(e -> Response.ok(e).build())
                .orElseGet(() -> Response.noContent().build());
    }

    /**
     * Comparer l'empreinte numérique.
     *
     * @param email Adresse de courriel
     * @param identifier Identifiant numérique
     * @return La valeur <code>true</code> si le calculé de l'empreinte
     * numérique donne le même résultat, sinon la valeur <code>false</code> est
     * retournée
     */
    private static boolean compare(final String email, final String identifier) {
        String hash = Hash.execute(Hash.MD5, email);
        return Objects.equals(hash, identifier);
    }

}
