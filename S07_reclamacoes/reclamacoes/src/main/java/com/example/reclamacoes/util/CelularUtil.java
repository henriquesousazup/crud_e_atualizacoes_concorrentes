package com.example.reclamacoes.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CelularUtil {

    public static String anonymize(String celular) {
        String formattedString = new StringBuilder(celular)
                .replace(0, celular.length() - 4, new String(new char[celular.length() - 4]).replace("\0", "x")).toString();
        return formattedString;
    }

    public static String hash(String celular) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA3-256");
            byte[] hash = digest.digest(celular.getBytes(StandardCharsets.UTF_8));
            return toHex(hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new IllegalStateException("Erro ao gerar hash de um celular: " + celular);
        }
    }

    private static String toHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte aByte : bytes) {
            result.append(String.format("%02X", aByte));
        }
        return result.toString();
    }

}
