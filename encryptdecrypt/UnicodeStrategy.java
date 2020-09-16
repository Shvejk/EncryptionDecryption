package encryptdecrypt;

public class UnicodeStrategy implements CryptoStrategy {

    @Override
    public String encrypt(String message, int key) {
        String result = "";
        for (int i = 0; i < message.length(); i++) {
            result += processChar(message.charAt(i), key);
        }
        return result;
    }

    @Override
    public String decrypt(String message, int key) {
        key = -key;
        return encrypt(message, key);
    }


    private char processChar(char ch, int key) {
        return ch += key;
    }
}
