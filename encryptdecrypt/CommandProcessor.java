package encryptdecrypt;

import java.nio.file.Files;
import java.nio.file.Paths;

public class CommandProcessor {
    String mode;
    int key;
    String data;
    String alg;
    String dataInFile;
    String inFileName;
    String outFileName;
    boolean isData;
    boolean isInFile;
    boolean isOutFile;

    public CommandProcessor(String[] commands) {
        //set default values
        mode = "enc";
        key = 0;
        data = "";
        alg = "shift";
        isData = false;
        isInFile = false;
        isOutFile = false;

        //parse commands and replace default values (if any)
        parseCommands(commands);
        //Prefer input String data over reading input file
        setData();
    }

    public String getMode() {
        return mode;
    }

    public int getKey() {
        return key;
    }

    public String getData() {
        return data;
    }

    public String getAlg() {
        return alg;
    }

    public String getOutFileName() {
        return this.outFileName;
    }

    private void parseCommands(String[] commands) {
        for (int i = 0; i < commands.length; i += 2) {
            String command = commands[i];
            switch (command) {
                case "-alg":
                    alg = commands[i + 1];
                    break;
                case "-mode":
                    mode = commands[i + 1];
                    break;
                case "-data":
                    data = commands[i + 1];
                    isData = true;
                    break;
                case "-key":
                    key = Integer.parseInt(commands[i + 1]);
                    break;
                case "-in":
                    inFileName = commands[i + 1];
                    isInFile = true;
                    dataInFile = readFile(inFileName);
                    break;
                case "-out":
                    outFileName = commands[i + 1];
                    isOutFile = true;
                    break;
            }
        }
    }

    private void setData() {
        if (!isData && isInFile) {
            data = dataInFile;
        }
    }

    private String readFile(String fileName) {
        try {
            return new String(Files.readAllBytes(Paths.get(fileName)));
        } catch (Exception e) {
            System.out.print("File does not exist");
        }
        return null;
    }
}