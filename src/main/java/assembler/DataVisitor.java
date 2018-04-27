

package assembler;

import assembler.grammar.assemblerParser;
import assembler.symboltable.SymbolTable;
import assembler.symboltable.SymbolType;


// pass 2: calculate data block label addresses, and save scope levels
public class DataVisitor extends BaseVisitor
{
    
    // constructor
    public DataVisitor(SymbolTable symbolTable, int startingAddress, boolean showDebugInfo)
    {
        this.symbolTable    = symbolTable;
        this.currentAddress = startingAddress;
        this.errorCount     = 0;
        this.showDebugInfo  = showDebugInfo;
    }
    
    
    @Override
    public Void visitBlock(assemblerParser.BlockContext ctx)
    {
        if(ctx.functionBlock() != null)
            visit(ctx.functionBlock());
        
        if(ctx.dataBlock() != null)
            visit(ctx.dataBlock());
        
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
        if(ctx.dataBlock() != null)
            visit(ctx.dataBlock());
        
        return null;
    }
    
    
    @Override
    public Void visitDataBlock(assemblerParser.DataBlockContext ctx)
    {
        if(ctx.dataDecl() != null)
            visit(ctx.dataDecl());
        
        if(ctx.dataLine() != null)
        {
            int dataLineCount = ctx.dataLine().size();
        
            // visit all data lines
            for(int i = 0; i < dataLineCount; i++)
                visit(ctx.dataLine(i));
        }
        
        return null;
    }
    
    
    @Override
    public Void visitDataLine(assemblerParser.DataLineContext ctx)
    {
        if(ctx.align() != null)
            visit(ctx.align());
        
        if(ctx.dataDecl() != null)
            visit(ctx.dataDecl());
        
        return null;
    }
    
    
    @Override
    public Void visitDataDecl(assemblerParser.DataDeclContext ctx)
    {
        if(ctx.ID() != null)
        {
            String name = ctx.ID().getText();
            
            if(!symbolTable.insertSymbol(name, SymbolType.DATALABEL, (long) currentAddress))
                reportError(ctx.ID().getSymbol(), "Label already defined!");
        }
        
        dataType = ctx.DATATYPE().getText();
        
        if(ctx.dataEntries()!= null)
            visit(ctx.dataEntries());
        
        if(ctx.dataArray() != null)
            visit(ctx.dataArray());
        
        return null;
    }
    
    
    @Override
    public Void visitDataEntries(assemblerParser.DataEntriesContext ctx)
    {
        int dataValueCount = ctx.dataValue().size();
        
        // visit all data values
        for(int i = 0; i < dataValueCount; i++)
        {
            visit(ctx.dataValue(i));
        
            switch(dataType)
            {
                case "bytes":  currentAddress += 1 * value; break;
                case "words":  currentAddress += 2 * value; break;
                case "dwords": currentAddress += 4 * value; break;
            }
        }
        
        return null;
    }
    
    
    @Override
    public Void visitDataArray(assemblerParser.DataArrayContext ctx)
    {
        visit(ctx.expression(0)); // get size
        
        if(value > 0)
        {
            switch(dataType)
            {
                case "bytes":  currentAddress += 1 * (int) value; break;
                case "words":  currentAddress += 2 * (int) value; break;
                case "dwords": currentAddress += 4 * (int) value; break;
            }
        }
        else
        {
            reportError(lastToken, "Array size can't be zero or negative!");
        }
        
        return null;
    }
    
    
    @Override
    public Void visitDataValue(assemblerParser.DataValueContext ctx)
    {
        if(ctx.STRING() != null)
            value = ctx.STRING().getText().length() - 2; // subtract 2 for the double quotes
        
        if(ctx.expression() != null)
            value = 1;
        
        return null;
    }
    
    
    @Override
    public Void visitAlign(assemblerParser.AlignContext ctx)
    {
        // get the expression value
        visit(ctx.expression());
        
        if(ctx.ALIGN() != null)
        {
            // check that the alignment value is non negative and a power of two
            if((value > 0) && ((value & (value - 1)) == 0))
            {
                int mask = (int) (value - 1);
                if((currentAddress & mask) != 0)   // check if the address is out of alignment
                {
                    currentAddress += (int) value; // if it is increment by the alignment value first
                    currentAddress &= ~mask;       // and zero out lower bits of the resulting address with the inverted mask
                }
            }
            else
            {
                reportError(lastToken, "Alignment value must be > 0 and a power of two!");
            }
        }
        
        return null;
    }
    
    
}

