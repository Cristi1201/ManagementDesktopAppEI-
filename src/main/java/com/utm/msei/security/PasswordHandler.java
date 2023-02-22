package com.utm.msei.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;

public class PasswordHandler {
    private static final int SALT_LENGTH = 16; // in bytes
    private static final int ITERATIONS = 10000;

    public static String generateSalt() {
        byte[] salt = new byte[SALT_LENGTH];
        SecureRandom random = new SecureRandom();
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    public static String hashPassword(String password, String salt) {
        try {
            byte[] saltBytes = Base64.getDecoder().decode(salt);
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.reset();
            digest.update(saltBytes);
            byte[] hash = digest.digest(password.getBytes());
            for (int i = 0; i < ITERATIONS; i++) {
                digest.reset();
                hash = digest.digest(hash);
            }
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getRecordFromSaltAndHash(String salt, String hash) {
        return salt + ":" + hash;
    }

    public static String[] getSaltAndHashFromRecord(String record) {
        String[] fields = record.split(":");
        String salt = fields[0];
        String hash = fields[1];
        return new String[] {salt, hash};
    }

    public static boolean validatePassword(String password, String salt, String storedHash) {
        String hash = hashPassword(password, salt);
        return storedHash.equals(hash);
    }
}
