package encryptdecrypt;


public class Main {
    public static void main(String[] args) {
        //CommandProcessor parses and stores command line arguments
        CommandProcessor commandProcessor = new CommandProcessor(args);

        //CryptoContext encapsulates algorithm selection and algorithm object instantiation
        CryptoContext cryptoContext = new CryptoContext(commandProcessor);

        //CryptoContext also encapsulates output by printing to main or writing to file
        cryptoContext.output(cryptoContext.executeAlgorithm());
    }
}


