import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String filename = args[0];
        if (filename.isBlank()) {
            System.err.println("Please provide the file name for the file containing the grammar in CNF");
            System.exit(1);
        }

        File grammarFile = new File(filename);
        if (!grammarFile.exists() || !grammarFile.isFile()) {
            System.err.println("The provided filename does not exist or is not a file");
            System.exit(1);
        }

        Grammar grammar = new ConcreteGrammar(grammarFile);
        System.out.println("Provide a string to parse: ");

        Scanner scanner = new Scanner(System.in);
        String parseString = scanner.nextLine();
        scanner.close();

        if (parseString != null && !parseString.isBlank()) {
            Parser parser = new Parser(grammar);
            parser.parse(parseString);
        }
    }
}

