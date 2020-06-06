package com.github.zelmothedragon.marianne.dataprovider;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Intercepteur d'exception web pour les convertir au format JSON.
 *
 * @author MOSELLE Maxime
 */
@Provider
public class WebApplicationExceptionMapper
        implements ExceptionMapper<WebApplicationException> {

    /**
     * Constructeur par défaut. Requis pour le fonctionnement des technologies de
     * Java EE.
     */
    public WebApplicationExceptionMapper() {
        // RAS
    }

    @Override
    public Response toResponse(final WebApplicationException exception) {

        JsonObject json = Json
                .createObjectBuilder()
                .add("error", exception.getClass().getSimpleName())
                .add("message", exception.getMessage())
                .build();

        return Response
                .status(exception.getResponse().getStatusInfo())
                .entity(json)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

}
