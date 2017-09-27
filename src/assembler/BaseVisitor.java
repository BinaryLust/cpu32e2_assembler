

package assembler;

import assembler.grammar.assemblerBaseVisitor;
import assembler.grammar.assemblerParser;
import static assembler.grammar.assemblerParser.AND;
import static assembler.grammar.assemblerParser.DIVIDE;
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
import assembler.symboltable.Entry;
import assembler.symboltable.SymbolTable;
import org.antlr.v4.runtime.Token;


public class BaseVisitor extends assemblerBaseVisitor<Void>
{
    protected SymbolTable symbolTable;
    protected long        value;
    protected Token       lastToken;
    protected int         errorCount;
    protected int         currentAddress;
    protected boolean     showDebugInfo;
    protected String      dataType;
    
    
    public int getCurrentAddress() { return currentAddress; }
    
    
    @Override
    public Void visitInput(assemblerParser.InputContext ctx)
    {
        int blockCount = ctx.block().size();
        
        // visit all blocks
        for(int i = 0; i < blockCount; i++)
            visit(ctx.block(i));
        
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
        else if(ctx.CHAR() != null) // CHAR
        {
            lastToken = ctx.CHAR().getSymbol();
            value = (long) ctx.CHAR().getText().charAt(1); // skip the apostrophe's
        }
        else // ID
        {
            lastToken = ctx.ID().getSymbol();
            String id = ctx.ID().getText();
            Entry  v  = (Entry) symbolTable.globalSearch(id);
            
            if(v == null)
            {
                value = 0;
                reportError(lastToken, "Constant does not exist!");
            }
            else
            {
                //if(v.type == SymbolType.MACRO)
                    //reportError(lastToken, "This is a macro not a constant!");
                
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
    
    
    public boolean hadErrors()
    {
        return (errorCount != 0);
    }
    
}
