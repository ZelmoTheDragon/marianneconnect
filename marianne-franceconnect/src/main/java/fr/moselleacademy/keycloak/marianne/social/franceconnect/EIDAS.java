package fr.moselleacademy.keycloak.marianne.social.franceconnect;

import com.google.common.base.Objects;
import java.util.List;

/**
 *
 * @author MOSELLE Maxime
 */
public enum EIDAS {

    LEVEL_1("eidas1"),
    LEVEL_2("eidas2"),
    LEVEL_3("eidas3"),
    EMPTY("null");

    public static final String QUERY_PARAM_ACR = "acr_values";

    private final String value;

    private EIDAS(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static EIDAS fromString(final String value) {
        EIDAS eidas = EMPTY;
        for (EIDAS e : values()) {
            if (Objects.equal(e.value, value)) {
                eidas = e;
            }
        }
        return eidas;
    }

    public static List<String> asList() {
        return List.of(
                LEVEL_1.value,
                LEVEL_2.value,
                LEVEL_3.value
        );
    }

}
