package fr.moselleacademy.marianne.dataprovider.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Classe utilitaire pour le hachage.
 *
 * @author MOSELLE Maxime
 */
public final class Hash {

    /**
     * Algorithme de hachache MD5.
     */
    public static final String MD5 = "MD5";

    /**
     * Algorithme de hachache SHA-1.
     */
    public static final String SHA1 = "SHA-1";

    /**
     * Algorithme de hachache SHA-224.
     */
    public static final String SHA224 = "SHA-224";

    /**
     * Algorithme de hachache SHA-256.
     */
    public static final String SHA256 = "SHA-256";

    /**
     * Algorithme de hachache SHA-384.
     */
    public static final String SHA384 = "SHA-384";

    /**
     * Algorithme de hachache SHA-512.
     */
    public static final String SHA512 = "SHA-512";

    /**
     * Constructeur interne.
     */
    private Hash() {
        throw new UnsupportedOperationException("No instance for you");
    }

    /**
     * Hacher une chaine de caratére.
     *
     * @param algorithm L'algorithme de hachage (voir les constantes de cette
     * classe)
     * @param word Le mot à hacher
     *
     * @return Le mot haché.
     */
    public static String execute(final String algorithm, final String word) {
        final StringBuilder hexaCode = new StringBuilder();
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            md.update(word.getBytes(StandardCharsets.UTF_8));
            final byte[] bytes = md.digest();
            for (byte b : bytes) {
                final String hexa = Integer.toHexString(0xFF & b);
                if (hexa.length() == 1) {
                    hexaCode.append('0');
                }
                hexaCode.append(hexa);
            }
        } catch (NoSuchAlgorithmException ex) {
            throw new IllegalArgumentException(ex);
        }
        return hexaCode.toString();
    }
}
