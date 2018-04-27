
/*package assembler.preprocessor;

import assembler.grammar.assemblerBaseVisitor;
import assembler.grammar.assemblerParser;
import static assembler.grammar.assemblerParser.AND;
import static assembler.grammar.assemblerParser.DIVIDE;
import static assembler.grammar.assemblerParser.LABEL;
import static assembler.grammar.assemblerParser.MINUS;
import static assembler.grammar.assemblerParser.MODULUS;
import static assembler.grammar.assemblerParser.MULTIPLY;
import static assembler.grammar.assemblerParser.NOT;
import static assembler.grammar.assemblerParser.OR;
import static assembler.grammar.assemblerParser.PLUS;
import static assembler.grammar.assemblerParser.SHIFTLEFT;
import static assembler.grammar.assemblerParser.SHIFTRIGHT;
import static assembler.grammar.assemblerParser.SHIFTRIGHTARITH;
import static assembler.grammar.assemblerParser.XOR;
import assembler.macrosymbol.MacroSymbolEntry;
import assembler.macrosymbol.MacroSymbolType;
import assembler.symboltable.ConstantEntry;
import assembler.symboltable.MacroEntry;
import assembler.symboltable.SymbolTable;
import assembler.symboltable.SymbolType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStreamRewriter;


public class PreprocessorVisitor extends assemblerBaseVisitor<Void>
{
    
    private SymbolTable                   symbolTable;
    private CommonTokenStream             original;  // the original tokens
    private TokenStreamRewriter           expanded;  // the expanded tokens
    
    private Map<String, MacroSymbolEntry> macroSymbols;
    private int                           macroParameters;
    private List<Token>                   macroTokens; 
    
    private long                          value;
    private Token                         lastToken;
    private int                           errorCount;
    
    private Token                         currentToken;
    private String                        currentText;
    private String                        currentParam;
    private String                        result;
    
    
    public PreprocessorVisitor(SymbolTable symbolTable, CommonTokenStream original, TokenStreamRewriter expanded)
    {
        this.symbolTable    = symbolTable;
        this.original       = original;
        this.expanded       = expanded;
    }
    
    
    @Override
    public Void visitInput(assemblerParser.InputContext ctx)
    {
        int macroCount = ctx.macro().size();
        
        // visit all macros
        for(int i = 0; i < macroCount; i++)
           visit(ctx.macro(i));
        
        
        int codeCount = ctx.code().size();
        
        // visit all code
        for(int i = 0; i < codeCount; i++)
           visit(ctx.code(i));
        
        return null;
    }

    
    @Override
    public Void visitCode(assemblerParser.CodeContext ctx)
    {
        // if lines exist visit them
        if(ctx.line() != null)
        {
            int lineCount = ctx.line().size();
        
            // visit all lines
            for(int i = 0; i < lineCount; i++)
                visit(ctx.line(i));
        }
        
        // if a function exists visit it
        if(ctx.function() != null)
            visit(ctx.function());
        
        return null;
    }
    
    
    @Override
    public Void visitMacro(assemblerParser.MacroContext ctx)
    {
        // this deletes macro definitions from the expanded code
        //expanded.delete(ctx.getStart(), ctx.getStop());
        
        // if CONST exists this is a constant otherwise its a macro
        if(ctx.CONST() != null)
        {
            int constCount = ctx.constassign().size();
        
            // visit all constant assignments
            for(int i = 0; i < constCount; i++)
                visit(ctx.constassign(i));
        }
        else // else this is a macro
        {
            String name = ctx.ID().getText();
            
            macroSymbols    = new HashMap<>();
            macroParameters = 0;
            
            if(ctx.param() != null)
                visit(ctx.param());
            
            int lineCount = ctx.macroLine().size();
            macroTokens = new ArrayList<>();
            
            // visit all lines of the macro
            for(int i = 0; i < lineCount; i++)
                visit(ctx.macroLine(i));
            
            if(!symbolTable.insertSymbol(name, new MacroEntry(SymbolType.MACRO, macroSymbols, macroParameters, macroTokens)))
                reportError(ctx.ID().getSymbol(), "Macro already defined!");
        }
        
        return null;
    }
    
    
    @Override public Void visitConstassign(assemblerParser.ConstassignContext ctx)
    {
        visit(ctx.expression());
        
        String name = ctx.ID().getText();
        
        if(!symbolTable.insertSymbol(name, new ConstantEntry(SymbolType.CONSTANT, (long) value)))
            reportError(ctx.ID().getSymbol(), "Constant already defined!");
        
        return null;
    }
    
    
    @Override
    public Void visitMacroLine(assemblerParser.MacroLineContext ctx)
    {
        // add label token to the macros token list
        if(ctx.LABEL() != null)
        {
            // add the label to the macro's local symbol table
            String name = ctx.LABEL().getText().substring(0, ctx.LABEL().getText().length()-1); // take off the colon
            Token  currentLabel = ctx.LABEL().getSymbol();
            
            if(macroSymbols.containsKey(name))
                reportError(currentLabel, "Label already defined!");
            else
                macroSymbols.put(name, new MacroSymbolEntry(MacroSymbolType.LABEL));
            
            // add the label to the token list
            macroTokens.add(original.get(ctx.LABEL().getSymbol().getTokenIndex()));
        }
        
        // add ignore tokens to the macros token list
        if(ctx.ignore() != null)
        {
            int start = ctx.ignore().getStart().getTokenIndex();
            int stop  = ctx.ignore().getStop().getTokenIndex();
            
            for(int i = start; i <= stop; i++)
                macroTokens.add(original.get(i));
        }
        
        // add another macros tokens to the macros token list
        //if(ctx.macroUse() != null)
        
        // add the NEWLINE token to the macros token list
        if(ctx.NEWLINE()!= null)
            macroTokens.add(original.get(ctx.NEWLINE().getSymbol().getTokenIndex()));
        
        return null;
    }
    
    
    @Override
    public Void visitParam(assemblerParser.ParamContext ctx)
    {
        for(int i = 0; i < ctx.ID().size(); i++)
        {
            String name             = ctx.ID(i).getText();
            Token  currentParameter = ctx.ID(i).getSymbol();
            
            if(macroSymbols.containsKey(name))
                reportError(currentParameter, "Parameter already defined!");
            else
                macroSymbols.put(name, new MacroSymbolEntry(MacroSymbolType.PARAMETER, i));
        }
        
        macroParameters = ctx.ID().size();
        
        return null;
    }
    
    
    @Override
    public Void visitParamValue(assemblerParser.ParamValueContext ctx)
    {
        currentParam = "";
        
        if(ctx.QUOTE() != null)
            currentParam = ctx.QUOTE().getText();
        else if(ctx.REG() != null)
            currentParam = ctx.REG().getText();
        else // imm()
        {
            if(ctx.imm().PLUS() != null)
                currentParam = ctx.imm().PLUS().getText();
            
            if(ctx.imm().MINUS() != null)
                currentParam = ctx.imm().MINUS().getText();
            
            if(ctx.imm().CHAR() != null)
                currentParam += ctx.imm().CHAR().getText();
            else // value()
            {
                if(ctx.imm().value().DNUM() != null)
                    currentParam += ctx.imm().value().DNUM().getText();
                else if(ctx.imm().value().HNUM() != null)
                    currentParam += ctx.imm().value().HNUM().getText();
                else if(ctx.imm().value().BNUM() != null)
                    currentParam += ctx.imm().value().BNUM().getText();
                else // ID()
                    currentParam += ctx.imm().value().ID().getText();
            }
                
        }
        
        return null;
    }
    
    
    @Override
    public Void visitLine(assemblerParser.LineContext ctx)
    {
        if(ctx.macroUse() != null)
        {
            visit(ctx.macroUse());
            
            expanded.replace(ctx.macroUse().getStart(), ctx.macroUse().getStop(), result); // replace macro usage with macro expansion
            expanded.delete(ctx.end().getStart(), ctx.end().getStop()); // delete end
        }
        
        return null;
    }
    
    
    @Override
    public Void visitFunction(assemblerParser.FunctionContext ctx)
    {
        symbolTable.enterScope();
        
        int lineCount = ctx.line().size();
        
        // visit all lines
        for(int i = 0; i < lineCount; i++)
           visit(ctx.line(i));
        
        symbolTable.exitScope();
        
        return null;
    }
    
    
    @Override
    public Void visitMacroUse(assemblerParser.MacroUseContext ctx)
    {
        String     macroName  = ctx.ID().getText();
        MacroEntry macroEntry = (MacroEntry) symbolTable.globalSearch(macroName);
        
        if(macroEntry != null)
        {
            if(macroEntry.type == SymbolType.MACRO)
            {
                ArrayList<String> paramValues = new ArrayList<>();
            
                // if parameter values exist grab them
                if(ctx.paramValue() != null)
                {
                    for(int i = 0; i < ctx.paramValue().size(); i++)
                    {
                        visit(ctx.paramValue(i));
                        paramValues.add(currentParam);
                    }
                }
                
                result = "";
                
                // compare the number of given parameter values to the number of paramater names of the macro
                if(macroEntry.macroParameters == paramValues.size())
                {
                    // cycle through all tokens
                    for(int i = 0; i < macroEntry.macroTokens.size(); i++)
                    {
                        // grab a token from the macro
                        currentToken = macroEntry.macroTokens.get(i);
                        currentText  = currentToken.getText();
                        
                        // if type is label, extract label name, compare it to the local
                        // macro symbol table. if the entry exists append times used to the
                        // name and then write it to the output text
                        // otherwise just write the original text back
                        
                        // check if this token is a label
                        if(currentToken.getType() == LABEL)
                        {
                            StringBuilder sb = new StringBuilder(currentText);
                            sb.insert(sb.length()-1, "m" + macroEntry.timesUsed); // append times used to string label to make unique label
                            currentText = sb.toString();
                        }
                        else
                        {
                            // if the text of this token matches a symbol of this macro swap the text of
                            // this symbol with the value of the parameter that was given.
                            // if instead this symbol match is a label then just append the times used
                            // to the labal to make a unique labal
                            if(macroEntry.macroSymbols.containsKey(currentText))
                            {
                                if(macroEntry.macroSymbols.get(currentText).type == MacroSymbolType.LABEL)
                                {
                                    StringBuilder sb = new StringBuilder(currentText);
                                    sb.insert(sb.length(), "m" + macroEntry.timesUsed); // append times used to string label to make unique label
                                    currentText = sb.toString();
                                }
                                else // its a parameter
                                {
                                    // if the token is the same as a parameter name we swap
                                    // the tokens text with the value matching the parameter
                                    // name that was given
                                    int num = macroEntry.macroSymbols.get(currentText).parameterNum;
                                    currentText = paramValues.get(num);
                                }
                            }
                        }
                        result += currentText;
                    }
                    macroEntry.timesUsed++;
                }
                else
                    reportError(ctx.ID().getSymbol(), "Mismatch in number of parameters");
            }
            else
                reportError(ctx.ID().getSymbol(), "Only macro functions can be used here");
        }
        else
            reportError(ctx.ID().getSymbol(), "Macro hasn't been defined!");
        
        return null;
    }
    
    
    @Override
    public Void visitImm(assemblerParser.ImmContext ctx)
    {
        if(ctx.value() != null)
            visit(ctx.value());
        else // CHAR
        {
            lastToken = ctx.CHAR().getSymbol();
            value = (long) ctx.CHAR().getText().charAt(1); // skip the apostrophe's
        }
        
        if(ctx.MINUS() != null)
            value = -value;
        
        return null;
    }
    
    
    @Override
    public Void visitValue(assemblerParser.ValueContext ctx)
    {
        if(ctx.DNUM() != null)
        {
            lastToken = ctx.DNUM().getSymbol();
            try {
                value = Long.parseLong(ctx.DNUM().getText(), 10);
            } catch (NumberFormatException e) {
                reportError(lastToken, "Decimal value out of bounds!");
                value = 0;
            }
        }
        else if(ctx.HNUM() != null)
        {
            lastToken = ctx.HNUM().getSymbol();
            try {
                String temp = ctx.HNUM().getText();
                value = Long.parseLong(temp.substring(0, temp.length()-1), 16);
            } catch (NumberFormatException e) {
                reportError(lastToken, "Hex value out of bounds!");
                value = 0;
            }
        }
        else if(ctx.BNUM() != null)
        {
            lastToken = ctx.BNUM().getSymbol();
            try {
                String temp = ctx.BNUM().getText();
                value = Long.parseLong(temp.substring(0, temp.length()-1), 2); // all but the last character
            } catch (NumberFormatException e) {
                reportError(lastToken, "Binary value out of bounds!");
                value = 0;
            }
        }
        else // ID
        {
            lastToken = ctx.ID().getSymbol();
            String        id = ctx.ID().getText();
            ConstantEntry v  = (ConstantEntry) symbolTable.globalSearch(id);
            
            if(v == null)
            {
                value = 0;
                reportError(lastToken, "Constant does not exist!");
            }
            else
            {
                if(v.type == SymbolType.MACRO)
                    reportError(lastToken, "This is a macro not a constant!");
                
                value = v.value;
            }
        }

        return null;
    }

    
    @Override
    public Void visitElement(assemblerParser.ElementContext ctx)
    {
        visit(ctx.atom());
        
        return null;
    }

    
    @Override
    public Void visitBinary(assemblerParser.BinaryContext ctx)
    {
        visit(ctx.expression(0));
        long var1 = value;
        visit(ctx.expression(1));
        long var2 = value;
        
        switch(ctx.op.getType())
        {
            case MULTIPLY:        value = var1 * var2; break;
            case DIVIDE:          value = var1 / var2; break;
            case MODULUS:         value = var1 % var2; break;
            case PLUS:            value = var1 + var2; break;
            case MINUS:           value = var1 - var2; break;
            case SHIFTLEFT:       value = var1 << var2;  break;
            case SHIFTRIGHT:      value = var1 >> var2;  break;
            case SHIFTRIGHTARITH: value = var1 >>> var2; break;
            case AND:             value = var1 & var2;   break;
            case XOR:             value = var1 ^ var2;   break;
            case OR:              value = var1 | var2;   break;
            default: break;
        }
        
        return null;
    }

    
    @Override
    public Void visitUnary(assemblerParser.UnaryContext ctx)
    {
        visit(ctx.atom());

        if(ctx.op.getType() == MINUS)
            value = -value;
        else if(ctx.op.getType() == NOT)
            value = ~value;
        
        return null;
    }

    
    @Override
    public Void visitVariable(assemblerParser.VariableContext ctx)
    {
        visit(ctx.value());
        
        return null;
    }

    
    @Override
    public Void visitParen(assemblerParser.ParenContext ctx)
    {
        visit(ctx.expression());
        
        return null;
    }
    
    
    public void reportError(Token pos, String message)
    {
        int line = pos.getLine();
        int start = pos.getCharPositionInLine();
        String text = pos.getText();
        String str = String.format("At Line %d Character %d -> \'%s\' ", line, start, text);
        System.err.println(str + message);
        errorCount++;
    }
    
    
    public boolean hasNoErrors()
    {
        return (errorCount == 0);
    }
    
}*/

