package encryptdecrypt;

import java.io.FileWriter;

public class CryptoContext {
    private CommandProcessor commandProcessor;
    private CryptoStrategy algorithm;

    public CryptoContext(CommandProcessor commandProcessor) {
        this.commandProcessor = commandProcessor;
        setAlgorithm();
    }

    private void setAlgorithm() {
        switch (commandProcessor.getAlg()) {
            case "unicode":
                algorithm = new UnicodeStrategy();
                break;
            default:
                algorithm = new ShiftStrategy();
        }

    }

    public String executeAlgorithm() {
        if (commandProcessor.getMode().equals("dec")) {
            return algorithm.decrypt(commandProcessor.getData(), commandProcessor.getKey());
        } else {
            return algorithm.encrypt(commandProcessor.getData(), commandProcessor.getKey());
        }
    }

    private void writeToFile(String processedData) {
        try (FileWriter fileWriter = new FileWriter(commandProcessor.getOutFileName())) {
            fileWriter.write(processedData);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void output(String processedData) {
        if (commandProcessor.isOutFile) {
            writeToFile(executeAlgorithm());
        } else {
            System.out.println(executeAlgorithm());
        }
    }
}
