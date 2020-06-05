package fr.moselleacademy.marianne.dataprovider.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author MOSELLE Maxime
 */
public final class CSV {

    /**
     * Journalisation.
     */
    private static final Logger LOGGER = Logger.getLogger(CSV.class.getName());

    /**
     * Séparateur de cellule.
     */
    private static final String CSV_SEPARATOR = ",";

    private CSV() {
    }

    public static List<String> readRawFile(final String path) {
        // Utilisation de BufferReader à la place de java.nio.Paths et java.nio.Files
        // Car non surporté lorsque cette application est déployée dans Keycloak.
        List<String> lines;
        try (
                InputStream stream = CSV.class.getClassLoader().getResourceAsStream(path);
                InputStreamReader streamReader = new InputStreamReader(stream, StandardCharsets.UTF_8);
                BufferedReader reader = new BufferedReader(streamReader)) {

            lines = reader
                    .lines()
                    .collect(Collectors.toList());

        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "File not found", ex);
            lines = new ArrayList<>();
        }
        return lines;
    }

    public static <E> List<E> readFile(
            final String path,
            final String header,
            final Function<String[], E> mapper) {

        return readRawFile(path)
                .stream()
                .filter(e -> !e.contains(header))
                .map(e -> e.split(CSV_SEPARATOR))
                .filter(e -> e.length == header.split(CSV_SEPARATOR).length)
                .map(mapper)
                .collect(Collectors.toList());
    }
}
