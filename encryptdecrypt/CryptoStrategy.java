package encryptdecrypt;

public interface CryptoStrategy {
    String encrypt(String message, int key);
    String decrypt(String message, int key);
}
