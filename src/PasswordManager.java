import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;

public class PasswordManager {

    private static final SecureRandom random = new SecureRandom();

    public static byte[] generateSalt() {
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }

    public static String hashPassword(String password, byte[] salt) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            md.update(salt);

            byte[] hashedBytes = md.digest(
                    password.getBytes(java.nio.charset.StandardCharsets.UTF_8)
            );

            return bytesToHex(hashedBytes);

        } catch (Exception e) {
            throw new RuntimeException("Password hashing failed!");
        }
    }

    public static boolean verifyPassword(
            String password, byte[] salt, String storedHash) {

        String newHash = hashPassword(password, salt);

        return MessageDigest.isEqual(
                newHash.getBytes(java.nio.charset.StandardCharsets.UTF_8),
                storedHash.getBytes(java.nio.charset.StandardCharsets.UTF_8)
        );
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();

        for (byte b : bytes) {
            result.append(String.format("%02x", b));
        }

        return result.toString();
    }
}
