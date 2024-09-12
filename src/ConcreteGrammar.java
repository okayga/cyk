import java.io.File;

public class ConcreteGrammar extends Grammar {
    public ConcreteGrammar(File file) {
        super(file);
    }

    @Override
    public void addNonterminalRule(String nonterminal, String rhs1, String rhs2) {

    }

    @Override
    public void addTerminalRule(String nonterminal, char terminal) {

    }
}
