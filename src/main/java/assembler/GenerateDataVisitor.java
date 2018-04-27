

package assembler;

import assembler.grammar.assemblerParser;
import assembler.symboltable.SymbolTable;
import java.util.ArrayList;


public class GenerateDataVisitor extends BaseVisitor
{
    private ArrayList<Character>  memory;
   
    
    public GenerateDataVisitor(SymbolTable symbolTable, ArrayList<Character> memory, int startingAddress, boolean showDebugInfo)
    {
        this.symbolTable    = symbolTable;
        this.memory         = memory;
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
    public Void visitAlign(assemblerParser.AlignContext ctx)
    {
        // get the expression value
        visit(ctx.expression());

        int mask      = (int) (value - 1);
        int neededAddress = currentAddress;
        
        if((neededAddress & mask) != 0)   // check if the address is out of alignment
        {
            neededAddress += (int) value; // if it is increment by the alignment value first
            neededAddress &= ~mask;       // and zero out lower bits of the resulting address with the inverted mask
        }
        
        int fillBytes = neededAddress - currentAddress;
        
        if(fillBytes != 0)
        {
            for(int i = 0; i < fillBytes; i++)
            {
                //if(showDebugInfo) System.out.println("Wrote filler byte at address: " + currentAddress);
                writeByte(0); // write filler bytes to memory to do the requested alignment
            }
        }
        
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
    
    
    @Override public Void visitDataDecl(assemblerParser.DataDeclContext ctx)
    {
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
            visit(ctx.dataValue(i));
        
        return null;
    }
    
    
    @Override
    public Void visitDataArray(assemblerParser.DataArrayContext ctx)
    {
        visit(ctx.expression(0)); // get size
        long size = value;
        visit(ctx.expression(1)); // get initial value
        long initVal = value;
        
        switch(dataType)
        {
            case "bytes":  if((initVal < -128) || (initVal > 255))
                               reportError(lastToken, "Initial value out of bounds!, Legal range is (-128) to (255)");
                           else
                           {
                               for(int i = 0; i < size; i++)
                                   writeByte(initVal);
                           }
                           break;
            case "words":  if((initVal < -32768) || (initVal > 65535))
                               reportError(lastToken, "Initial value out of bounds!, Legal range is (-32,768) to (65,535)");
                           else
                           {
                               for(int i = 0; i < size; i++)
                                   writeWord(initVal);
                           }
                           break;
            case "dwords": if((initVal < -2147483648L) || (initVal > 4294967295L))
                               reportError(lastToken, "Initial value out of bounds!, Legal range is (-2,147,483,648) to (4,294,967,295)");
                           else
                           {
                               for(int i = 0; i < size; i++)
                                   writeDword(initVal);
                           }
                           break;
        }
        
        return null;
    }
    
    
    @Override
    public Void visitDataValue(assemblerParser.DataValueContext ctx)
    {
        if(ctx.STRING() != null)
        {
            int    length = (ctx.STRING().getText().length() - 2); // subtract 2 for the quotation marks
            String text   = ctx.STRING().getText().substring(1, ctx.STRING().getText().length()-1); // strip the quotes from string
            
            for(int i = 0; i < length; i++)
            {
                value = (long) text.charAt(i);
                
                if(showDebugInfo)
                    System.out.println(String.format("%-20S %02X       %-20S %08X", "Writing Byte:", value, "At Address:", currentAddress));
                
                switch(dataType)
                {
                    case "bytes":  writeByte(value);  break;
                    case "words":  writeWord(value);  break;
                    case "dwords": writeDword(value); break;
                }
            }
        }
    
        if(ctx.expression() != null)
        {
            visit(ctx.expression());
            
            switch(dataType)
            {
                case "bytes":  if((value < -128) || (value > 255))
                                   reportError(lastToken, "Byte value out of bounds!, Legal range is (-128) to (255)");
        
                               if(showDebugInfo)
                                   System.out.println(String.format("%-20S %02X       %-20S %08X", "Writing Byte:", value & 0xff, "At Address:", currentAddress));
        
                               writeByte(value);
                               break;
                case "words":  if((value < -32768) || (value > 65535))
                                   reportError(lastToken, "Word value out of bounds!, Legal range is (-32,768) to (65,535)");
        
                               if(showDebugInfo)
                                   System.out.println(String.format("%-20S %04X     %-20S %08X", "Writing Word:", value & 0xffff, "At Address:", currentAddress));
        
                               writeWord(value);
                               break;
                case "dwords": if((value < -2147483648L) || (value > 4294967295L))
                                   reportError(lastToken, "DWord value out of bounds!, Legal range is (-2,147,483,648) to (4,294,967,295)");
         
                               if(showDebugInfo)
                                   System.out.println(String.format("%-20S %08X %-20S %08X", "Writing DWord:", value, "At Address:", currentAddress));
        
                               writeDword(value);
                               break;
            }
        }

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
    
    
    public Void writeDword(long dword)
    {
        memory.add((char) ((dword >> 24) & 0xff));
        memory.add((char) ((dword >> 16) & 0xff));
        memory.add((char) ((dword >> 8) & 0xff));
        memory.add((char) (dword & 0xff));
        currentAddress += 4;
        
        return null;
    }
    
    
    public Void writeWord(long word)
    {
        memory.add((char) ((word >> 8) & 0xff));
        memory.add((char) (word & 0xff));
        currentAddress += 2;
        
        return null;
    }
    
    
    public Void writeByte(long nbyte)
    {
        memory.add((char) (nbyte & 0xff));
        currentAddress++;
        
        return null;
    }
}
