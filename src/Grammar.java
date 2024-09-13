import java.io.File;
import java.util.*;

// TODO: need to change the datatypes of the "rules" to improve performance and make rest of code reflect the changes
// ^ primarily make sure that arraylist works as is to be able to loop as nonterminalRules[i][j]
public abstract class Grammar {
     private final Map<String, String> terminalRules;
     private final ArrayList<ArrayList<String>> nonterminalRules;
     private final Map<String, Integer> nonterminalToInt;
     private final Map<Integer, String> intToNonterminal;

    public Grammar(File file) {
        terminalRules = new HashMap<>();
        nonterminalRules = new ArrayList<>();
        nonterminalToInt = new HashMap<>();
        intToNonterminal = new HashMap<>();

        fillAllRules(file);
    }

    // Initializes the "rules" with the rules specified in the grammar from the input file
    private void fillAllRules(File file) {
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();

                String[] parts = line.split(" -> ");
                if (parts.length != 2) {
                    throw new IllegalArgumentException("Invalid rule format: " + line);
                }

                String lhs = parts[0].trim();
                String rhs = parts[1].trim();

                if (lhs.length() != 1 || !Character.isUpperCase(lhs.charAt(0))) {
                    throw new IllegalArgumentException("Invalid nonterminal on the left-hand side: " + lhs);
                }

                if (!nonterminalToInt.containsKey(lhs)) {
                    nonterminalToInt.put(lhs, nonterminalRules.);
                }
                int lhsInt = nonterminalToInt.get(lhs);

                if (rhs.length() == 1 && (Character.isLowerCase(rhs.charAt(0)) || !Character.isLetterOrDigit(rhs.charAt(0)))) {
                    // Terminal rule
                    terminalRules.put(lhs, rhs);
                } else if (rhs.length() == 2 && Character.isUpperCase(rhs.charAt(0)) && Character.isUpperCase(rhs.charAt(1))) {
                    // Nonterminal rule
                    nonterminalRules.get(lhsInt).add(rhs);
                } else {
                    throw new IllegalArgumentException("Invalid right-hand side: " + rhs);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public abstract void addTerminalRule(String nonterminal, char terminal);

    public abstract void addNonterminalRule(String nonterminal, String rhs1, String rhs2);

    public String getTerminalRules(char nonterminal) {
        return terminalRules.get(nonterminalInt);
    }

    public ArrayList<String> getNonterminalRules(int nonterminalInt) {
        return nonterminalRules.get(nonterminalInt);
    }

    public ArrayList<ArrayList<String>> getAllNonterminalRules() {
        return nonterminalRules;
    }

    public int convertNonterminalToInt(String nonterminal) {
        return nonterminalToInt.get(nonterminal);
    }

    public String convertIntToNonterminal(int nonterminal) {
        return intToNonterminal.get(nonterminal);
    }

    public String getFirstNonterminal() {
        return firstNonterminal;
    }
}
