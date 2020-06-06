package com.github.zelmothedragon.marianne.keycloak.util;

import java.util.ResourceBundle;

/**
 *
 * @author MOSELLE Maxime
 */
public final class Messages {

    private static final ResourceBundle MESSAGE = ResourceBundle.getBundle("messages");

    private Messages() {
        throw new UnsupportedOperationException("No instance for you");
    }

    public static String get(final String key) {
        String message;
        if (MESSAGE.containsKey(key)) {
            message = MESSAGE.getString(key);
        } else {
            message = "???" + key + "???";
        }
        return message;
    }

}
