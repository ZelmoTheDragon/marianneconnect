package com.github.zelmothedragon.marianne.dataprovider.discovery;

import com.github.zelmothedragon.marianne.dataprovider.util.CSV;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Point d'entrée web pour la découverte d'URL.
 *
 * @author MOSELLE Maxime
 */
@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DiscoveryEndPoint {

    /**
     * Emplacement du fichier de découverte.
     */
    private static final String DISCOVERY_FILE = "/discovery.json";

    /**
     * Constructeur par défaut. Requis pour le fonctionnement des technologies
     * de Java EE.
     */
    public DiscoveryEndPoint() {
        // RAS
    }

    /**
     * Ping.
     *
     * @return Un message au format JSON
     */
    @GET
    @Path("/")
    public Response ping() {

        JsonObject ping = Json
                .createObjectBuilder()
                .add("ping", "hello world")
                .build();

        return Response
                .ok(ping)
                .build();
    }

    /**
     * Obtenir les informations sur cette API.
     *
     * @return Les informations sur cette API
     */
    @GET
    @Path("/discovery")
    public Response showEndPoint() {

        String json = CSV
                .readFile(DISCOVERY_FILE)
                .stream()
                .reduce("", String::concat);

        return Response
                .ok(json)
                .build();
    }

}
