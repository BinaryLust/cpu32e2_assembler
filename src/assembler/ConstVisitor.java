

package assembler;

import assembler.grammar.assemblerParser;
import assembler.symboltable.SymbolTable;
import assembler.symboltable.SymbolType;


// pass 3: calculate constant block values, save scope levels
public class ConstVisitor extends BaseVisitor
{
    // constructor
    public ConstVisitor(SymbolTable symbolTable, boolean showDebugInfo)
    {
        this.symbolTable    = symbolTable;
        this.errorCount     = 0;
        this.showDebugInfo  = showDebugInfo;
    }
    
    
    @Override
    public Void visitBlock(assemblerParser.BlockContext ctx)
    {
        if(ctx.functionBlock() != null)
            visit(ctx.functionBlock());
        
        if(ctx.constBlock() != null)
            visit(ctx.constBlock());
        
        return null;
    }
    
    
    @Override
    public Void visitFunctionBlock(assemblerParser.FunctionBlockContext ctx)
    {
        String name = ctx.ID().getText();
        
        symbolTable.enterScope(name);
        
        int functionLineCount = ctx.functionLine().size();
        
        // visit all function lines
        for(int i = 0; i < functionLineCount; i++)
            visit(ctx.functionLine(i));
        
        symbolTable.exitScope();
        
        return null;
    }
    
    
    @Override
    public Void visitFunctionLine(assemblerParser.FunctionLineContext ctx)
    {
        if(ctx.constBlock() != null)
            visit(ctx.constBlock());
        
        return null;
    }
    
    @Override
    public Void visitConstBlock(assemblerParser.ConstBlockContext ctx)
    {
        if(ctx.constDecl() != null)
            visit(ctx.constDecl());
        
        if(ctx.constLine() != null)
        {
            int constLineCount = ctx.constLine().size();
        
            // visit all constant lines
            for(int i = 0; i < constLineCount; i++)
                visit(ctx.constLine(i));
        }
        
        return null;
    }

    
    @Override
    public Void visitConstLine(assemblerParser.ConstLineContext ctx)
    {
        if(ctx.constDecl() != null)
            visit(ctx.constDecl());
        
        return null;
    }
    
    
    @Override
    public Void visitConstDecl(assemblerParser.ConstDeclContext ctx)
    {
        visit(ctx.expression());
        
        String name = ctx.ID().getText();
        
        if(!symbolTable.insertSymbol(name, SymbolType.CONSTANT, (long) value))
            reportError(ctx.ID().getSymbol(), "Constant already defined!");
        
        return null;
    }
}

