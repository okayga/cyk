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

public class Parser {
    private final Grammar theGrammar;
    private final char[] charArray;

    public Parser(Grammar grammar) {
        theGrammar = grammar;
    }

    private parse(String string) {
        charArray = string.toCharArray();
        parseNaive(S, 0, n);
    }

    public boolean parseNaive(int S, int i, int j) {
        charArray = theString.toCharArray();
        if (i == j - 1) {
            return()
        }
    }

    public boolean parseBU(int S, int i, int j) {

    }

    public boolean parseTD() {

    }
}
