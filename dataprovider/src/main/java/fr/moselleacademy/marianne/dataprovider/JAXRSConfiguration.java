package fr.moselleacademy.marianne.dataprovider;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * Classe de configuration de JAX-RS.
 *
 * @author MOSELLE Maxime
 */
@ApplicationPath("/api")
public class JAXRSConfiguration extends Application {

    /**
     * Constructeur par défaut. Requis pour le fonctionnement des technologies de
     * Java EE.
     */
    public JAXRSConfiguration() {
        // RAS
    }

}
