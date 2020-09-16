package encryptdecrypt;

public class ShiftStrategy implements CryptoStrategy {
    private char shiftConstant;

    @Override
    public String encrypt(String message, int key) {
        if (key < 0) {
            key = 26 - (-key % 26);
        }

        String result = "";
        for (int i = 0; i < message.length(); i++) {
            char ch = message.charAt(i);
            result += processChar(ch, key);
        }
        return result;
    }

    @Override
    public String decrypt(String message, int key) {
        return encrypt(message, -key);
    }

    private char processChar(char ch, int key) {
        if (!Character.isLetter(ch)) {
            return ch;
        }

        if (Character.isUpperCase(ch)) {
            shiftConstant = 'A';
        } else {
            shiftConstant = 'a';
        }
        return (char) ((ch - shiftConstant + key) % 26 + shiftConstant);
    }
}
