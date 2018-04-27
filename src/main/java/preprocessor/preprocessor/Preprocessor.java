

/*package assembler.preprocessor;

import assembler.CustomErrorListener;
import assembler.grammar.assemblerLexer;
import assembler.grammar.assemblerParser;
import assembler.symboltable.SymbolTable;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStreamRewriter;


public class Preprocessor
{
    private SymbolTable symbolTable;
    private String      expandedCode;
    
    
    public Preprocessor(SymbolTable symbolTable)
    {
        this.symbolTable    = symbolTable;
    }
    
    
    public boolean preprocess(String str)
    {
        // create an error listener
        CustomErrorListener          errorListener = new CustomErrorListener();
        
        ANTLRInputStream             input         = new ANTLRInputStream(str);
        assemblerLexer               lexer         = new assemblerLexer(input);
        lexer.removeErrorListeners();
        lexer.addErrorListener(errorListener);
        
        CommonTokenStream            tokens        = new CommonTokenStream(lexer);
        assemblerParser              parser        = new assemblerParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(errorListener);
        
        assemblerParser.InputContext tree        = parser.input();
        
        // if no errors occured then process the resulting tree
        if(errorListener.hasNoErrors())
        {
            // from the book
            // The key is the TokenStreamRewriter object that knows how to give altered views
            // of a token stream without actually modifying the stream. It treats all of the
            // manipulation methods as “instructions” and queues them up for lazy execution
            // when traversing the token stream to render it back as text. The rewriter executes
            // those instructions every time we call getText().

            TokenStreamRewriter expanded = new TokenStreamRewriter(tokens);
            
            // do the preprocessing
            System.out.println("Preprocessing starting");
            
            PreprocessorVisitor pass0 = new PreprocessorVisitor(symbolTable, tokens, expanded);
            pass0.visit(tree);
            
            System.out.println("Preprocessing finished");
            
            expandedCode = expanded.getText();
            return pass0.hasNoErrors(); // return the result of the preprocessing pass
        }
        
        return false;
    }
    
    
    public String getExpandedCode()
    {
        return expandedCode;
    }
}*/

