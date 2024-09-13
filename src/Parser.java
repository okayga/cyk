/*
A datatype Parser which is provided with a grammar to be parsed as an argument, and which provides
parsing methods such as parseNaive, parseBU, and parseTD. The methods should take the
string to be parsed as an argument, and should of course return a truth value indicating whether
the input string was found to belong to the language or not. In addition, the parser should contain
a counter which is initialized to zero when parsing starts.

The iterative parser (i.e., the standard bottom-up CYK parser) increments the counter with each execution of the innermost loop.
Similarly, the recursive variants increment the counter each time a recursive call is made. In this way,
the counter gives an estimate of the number of operations that have been executed when parsing has
finished. This yields an abstract measure of how much work the algorithm has performed and can
later on be compared to the physical time measurements in order to see whether the two types of
measurements support the same conclusions.
*/

import java.util.List;

// TODO: Need to update code to reflect changes in Grammar class
public class Parser {
    private final Grammar theGrammar;
    private char[] charArray = null;
    private int counterNaive;
    private int counterBU;
    private int counterTD;

    public Parser(Grammar grammar) {
        theGrammar = grammar;
        counterNaive = 0;
        counterBU = 0;
        counterTD = 0;
    }

    private void parse(String string) {
        charArray = string.toCharArray();
        int n = charArray.length;
        int S = theGrammar.convertNonterminalToInt(theGrammar.getFirstNonterminal());
        if (charArray != null && n != 0) {
            if (parseNaive(S, 0, n) && parseBU(S, 0, n) && parseTD(S, 0, n)) {
                System.out.println("String belongs to given grammar");
                System.out.println("Naive operations: " + counterNaive);
                System.out.println("Bottom up operations: " + counterBU);
                System.out.println("Top down operations: " + counterTD);
            } else {
                System.out.println("String does not belong to given grammar");
            }
        }
    }

    public boolean parseNaive(int S, int i, int j) {
        if (i == j - 1) {
            counterNaive++;
            char terminal = charArray[i];
            List<String> terminalRules = theGrammar.getTerminalRules(terminal);
            return terminalRules.contains(theGrammar.convertIntToNonterminal(S));
        } else {
            List<int[]> nonterminalRules = theGrammar.getNonterminalRules(S);
            for (int[] rule : nonterminalRules) {
                int B = rule[0];
                int C = rule[1];
                for (int k = i + 1; k < j; k++) {
                    counterNaive++;
                    if (parseNaive(B, i, k) && parseNaive(C, k, j)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean parseBU(int S, int i, int j) {
        int n = charArray.length;
        Boolean[][][] table = new Boolean[theGrammar.getAllNonterminalRules().length][n][n + 1];

        fillTableBU(n, table);

        for (int span = 2; span <= n; span++) {
            for (int start = 0; start <= n - span; start++) {
                int end = start + span;
                for (int split = start + 1; split < end; split++) {
                    for (int A = 0; A < theGrammar.getAllNonterminalRules().length; A++) {
                        List<int[]> nonterminalRules = theGrammar.getNonterminalRules(A);
                        for (int[] rule : nonterminalRules) {
                            counterBU++;
                            int B = rule[0];
                            int C = rule[1];
                            if (table[B][start][split] && table[C][split][end]) {
                                table[A][start][end] = true;
                                break;
                            }
                        }
                    }
                }
            }
        }

        return table[S][i][j];
    }

    private void fillTableBU(int n, Boolean[][][] table) {
        for (int s = 0; s < n; s++) {
            char terminal = charArray[s];
            List<String> terminalRules = theGrammar.getTerminalRules(terminal);
            for (String nonterminal : terminalRules) {
                int A = theGrammar.convertNonterminalToInt(nonterminal);
                table[A][s][s + 1] = true;
            }
        }
    }

    public boolean parseTD(int S, int i, int j) {
        int n = charArray.length;
        Boolean[][][] table = new Boolean[theGrammar.getAllNonterminalRules().length][n][n + 1];

        if (table[S][i][j] != null) {
            return table[S][i][j];
        }

        boolean result;
        if (i == j - 1) {
            counterTD++;
            char terminal = charArray[i];
            List<String> terminalRules = theGrammar.getTerminalRules(terminal);
            result = terminalRules.contains(theGrammar.convertIntToNonterminal(S));
        } else {
            result = false;
            List<int[]> nonterminalRules = theGrammar.getNonterminalRules(S);
            for (int[] rule : nonterminalRules) {
                int B = rule[0];
                int C = rule[1];
                for (int k = i + 1; k < j; k++) {
                    counterTD++;
                    if (parseTD(B, i, k) && parseTD(C, k, j)) {
                        result = true;
                        break;
                    }
                }
                if (result) {
                    break;
                }
            }
        }

        table[S][i][j] = result;
        return table[S][i][j];
    }
}
