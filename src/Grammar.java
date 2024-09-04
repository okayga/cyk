import java.util.*;

/*
An abstract datatype Grammar that can be subclassed by specifying the rules of a context-free gram-
mar in Chomsky normal form. The class should allow efficient access to the rules, where efficiency
should be judged relative to the way in which the rules are accessed in the CYK algorithm. In par-
ticular, it is probably a good idea to represent the terminal rules separately from the nonterminal
ones. Moreover, I suggest to translate the nonterminals into integers while initializing the internal
data structures. In this way, one may for example represent a right-hand side as a pair of integers,
and can implement the set of nonterminal rules as an array of arrays of right-hand sides. Then, if a
given nonterminal i has n nonterminal rules, one can efficiently loop over their right-hand sides as
rule[i][j] for j = 0, . . . , n − 1 (and one can loop over all rules by additionally looping over i).
*/

public abstract class Grammar {
     private Map<Character, List<String>> terminalRules;
     private List<int[]>[] nonterminalRules;
     private Map<String, Integer> nonterminalToInt;
     private Map<Integer, String> intToNonterminal;

    public Grammar(Set<String> nonterminals, Set<Character> terminals) {
        terminalRules = new HashMap<>();
        nonterminalRules = new ArrayList[nonterminals.size()];
        nonterminalToInt = new HashMap<>();
        intToNonterminal = new HashMap<>();

        int index = 0;
        for (String nonterminal : nonterminals) {
            nonterminalToInt.put(nonterminal, index);
            intToNonterminal.put(index, nonterminal);
            nonterminalRules[index] = new ArrayList<>();
            index++;
        }

        for (Character terminal : terminals) {
            terminalRules.put(terminal, new ArrayList<>());
        }
    }

    public abstract void addTerminalRule(String nonterminal, char terminal);

    public abstract void addNonterminalRule(String nonterminal, String rhs1, String rhs2);

    public List<String> getTerminalRules(char terminal) {
        return terminalRules.get(terminal);
    }

    public List<int[]> getNonterminalRules(int nonterminal) {
        return nonterminalRules[nonterminal];
    }

    public List<int[]>[] getAllNonterminalRules() {
        return nonterminalRules;
    }

    public int getNonterminalInt(String nonterminal) {
        return nonterminalToInt.get(nonterminal);
    }

    public String getNonterminalString(int nonterminal) {
        return intToNonterminal.get(nonterminal);
    }
}
