

package assembler;

import assembler.grammar.assemblerLexer;
import assembler.grammar.assemblerParser;
import java.util.ArrayList;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
//import assembler.preprocessor.Preprocessor;
import assembler.symboltable.SymbolTable;


// break file in to tokens
// parse tokens in to syntax trees and check everything is spelled correctly
// walk the syntax tree and collect all label/addresses and constants/values
// walk again and determine the type of each instruction from the tree, then
// use the type to lookup the opcode/rcode of the instruction, and what each
// field that we grabbed earlier maps to


public class Assembler
{
    public  ArrayList<Integer> data;
    
    
    public Assembler()
    {
        this.data           = new ArrayList<>();
    }
    
    
    public boolean assemble(String str, boolean showDebugInfo)
    {
        // create a symbol table
        SymbolTable symbolTable = new SymbolTable();

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
        
        // parse input and return parse tree
        assemblerParser.InputContext tree          = parser.input();
        
        // if errors occured then return false
        if(errorListener.hadErrors())
            return false;
        
        // do code pass
        System.out.println("Code Pass starting");
        
        // Visitor
        symbolTable.resetScope();
        CodeVisitor pass1 = new CodeVisitor(symbolTable, showDebugInfo);
        pass1.visit(tree);
        
        System.out.println("Code Pass finished");
        
        if(pass1.hadErrors())
            return false;
        
        // do data pass
        System.out.println("Data Pass starting");
        
        // Visitor
        symbolTable.resetScope();
        DataVisitor pass2 = new DataVisitor(symbolTable, pass1.getCurrentAddress(), showDebugInfo);
        pass2.visit(tree);
        
        System.out.println("Data Pass finished");

        if(pass2.hadErrors())
            return false;
        
        // do data pass
        System.out.println("Constant Pass starting");
        
        // Visitor
        symbolTable.resetScope();
        ConstVisitor pass3 = new ConstVisitor(symbolTable, showDebugInfo);
        pass3.visit(tree);
        
        System.out.println("Constant Pass finished");
        
        if(showDebugInfo)
        {
            System.out.println("");
            System.out.println("Symbol Table");
            symbolTable.dumpTable();
            System.out.println("");
            System.out.println("Scope Tree");
            symbolTable.dumpScopeTree();
            System.out.println("");
        }

        if(pass3.hadErrors())
            return false;
        
        // allocate memory to store the resulting code in
        ArrayList<Character> memory = new ArrayList<>();
        
        // do data pass
        System.out.println("Code Generation Pass starting");
        
        // Visitor
        symbolTable.resetScope();
        GenerateCodeVisitor pass4 = new GenerateCodeVisitor(symbolTable, memory, showDebugInfo);
        pass4.visit(tree);
        
        System.out.println("Code Generation Pass finished");

        if(pass4.hadErrors())
            return false;
        
        // do data pass
        System.out.println("Data Generation Pass starting");
        
        // Visitor
        symbolTable.resetScope();
        GenerateDataVisitor pass5 = new GenerateDataVisitor(symbolTable, memory, pass4.getCurrentAddress(), showDebugInfo);
        pass5.visit(tree);
        
        System.out.println("Data Generation Pass finished");

        if(pass5.hadErrors())
            return false;
        
        // convert byte array to dword memory block
        for(int address = 0; address < memory.size(); address += 4)
        {
            int value = 0;
            if(address < memory.size())     value |= ((int) memory.get(address))   << 24;
            if((address+1) < memory.size()) value |= ((int) memory.get(address+1)) << 16;
            if((address+2) < memory.size()) value |= ((int) memory.get(address+2)) << 8;
            if((address+3) < memory.size()) value |= ((int) memory.get(address+3));
            data.add(value);
        }
        
        return true;
    }
}

