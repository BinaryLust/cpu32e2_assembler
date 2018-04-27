

package assembler;

import assembler.encoder.Encoder;
import assembler.grammar.assemblerParser;
import assembler.symboltable.SymbolTable;
import java.util.ArrayList;


// pass 4: generate code and data, output machine code
public class GenerateCodeVisitor extends BaseVisitor
{
    private ArrayList<Character>  memory;
    
    // encoder
    private Encoder               encoder;
    
    
    public GenerateCodeVisitor(SymbolTable symbolTable, ArrayList<Character> memory, boolean showDebugInfo)
    {
        this.symbolTable    = symbolTable;
        this.memory         = memory;
        this.currentAddress = 0;
        this.errorCount     = 0;
        this.encoder        = new Encoder(showDebugInfo);
        this.showDebugInfo  = showDebugInfo;
    }
    
    
    @Override
    public Void visitBlock(assemblerParser.BlockContext ctx)
    {
        if(ctx.masterLine() != null)
        {
            int masterLineCount = ctx.masterLine().size();
        
            // visit all master lines
            for(int i = 0; i < masterLineCount; i++)
                visit(ctx.masterLine(i));
        }
        
        if(ctx.functionBlock() != null)
            visit(ctx.functionBlock());

        return null;
    }
    
    
    @Override
    public Void visitMasterLine(assemblerParser.MasterLineContext ctx)
    {
        if(ctx.inst() != null)
        {
            // set default instruction type values
            encoder.setName("xxx");
            encoder.setSize("xxx");
            encoder.setAdr("xxx");
            encoder.setField1(0);
            encoder.setField2(0);
            encoder.setField3(0);
            encoder.setField4(0);
            
            visit(ctx.inst());
            
            //writeInstruction();
            if(!encoder.encodeInstruction())
                reportError(lastToken, encoder.getErrorMessage());
            
            writeDword(encoder.getInstruction());
            //mainController.println(String.format("%-20S %08X %-20S %08X", "Writing Instruction:", encoder.getInstruction(), "At Address:", currentAddress));
        }
        
        if(ctx.dataDecl() != null)
            visit(ctx.dataDecl());
        
        if(ctx.origin() != null)
            visit(ctx.origin());
        
        if(ctx.align() != null)
            visit(ctx.align());

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
    public Void visitOrigin(assemblerParser.OriginContext ctx)
    {
        // get the expression value
        visit(ctx.expression());

        currentAddress = (int) value;
        
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
        if(ctx.inst() != null)
        {
            // set default instruction type values
            encoder.setName("xxx");
            encoder.setSize("xxx");
            encoder.setAdr("xxx");
            encoder.setField1(0);
            encoder.setField2(0);
            encoder.setField3(0);
            encoder.setField4(0);
            
            visit(ctx.inst());
            
            //writeInstruction();
            if(!encoder.encodeInstruction())
                reportError(lastToken, encoder.getErrorMessage());
            
            writeDword(encoder.getInstruction());
            //mainController.println(String.format("%-20S %08X %-20S %08X", "Writing Instruction:", encoder.getInstruction(), "At Address:", currentAddress));
        }
        
        if(ctx.dataDecl() != null)
            visit(ctx.dataDecl());
        
        if(ctx.align() != null)
            visit(ctx.align());
        
        return null;
    }
    
    
    @Override
    public Void visitImm(assemblerParser.ImmContext ctx)
    {
        visit(ctx.expression());
        
        if(ctx.MINUS() != null)
            value = -value;
        
        return null;
    }
    
    
    @Override
    public Void visitTypeR0(assemblerParser.TypeR0Context ctx)
    {
        encoder.setName(ctx.OR0().getText());
        
        return null;
    }
    
    
    @Override
    public Void visitTypeSR1(assemblerParser.TypeSR1Context ctx)
    {
        encoder.setName(ctx.OSR1().getText());
        encoder.setAdr("reg");
        encoder.setField1(sysReg2Int(ctx.SYSREG().getText()));
        encoder.setField2(reg2Int(ctx.REG().getText()));
        
        return null;
    }
    
    
    @Override
    public Void visitTypeRS1(assemblerParser.TypeRS1Context ctx)
    {
        encoder.setName(ctx.ORS1().getText());
        encoder.setAdr("reg");
        encoder.setField1(reg2Int(ctx.REG().getText()));
        encoder.setField2(sysReg2Int(ctx.SYSREG().getText()));
        
        return null;
    }
    
    
    @Override
    public Void visitTypeI1(assemblerParser.TypeI1Context ctx)
    {
        encoder.setName(ctx.OI1().getText());
        encoder.setAdr("imm");
        visit(ctx.expression());
        encoder.setField1((int) value);
        
        return null;
    }
    
    
    @Override
    public Void visitTypeIR(assemblerParser.TypeIRContext ctx)
    {
        encoder.setName(ctx.IR().getText());
        encoder.setAdr("rgi");
        encoder.setField1(reg2Int(ctx.REG().getText()));
        
        return null;
    }
    
    
    @Override
    public Void visitTypeR2A(assemblerParser.TypeR2AContext ctx)
    {
        encoder.setName(ctx.RI2().getText());
        encoder.setAdr("reg");
        encoder.setField1(reg2Int(ctx.REG(0).getText()));
        encoder.setField2(reg2Int(ctx.REG(1).getText()));
        
        return null;
    }
    
    
    @Override
    public Void visitTypeI2(assemblerParser.TypeI2Context ctx)
    {
        encoder.setName(ctx.RI2().getText());
        encoder.setAdr("imm");
        encoder.setField1(reg2Int(ctx.REG().getText()));
        visit(ctx.expression());
        encoder.setField2((int) value);
        
        return null;
    }
    
    
    @Override
    public Void visitTypeR2B(assemblerParser.TypeR2BContext ctx)
    {
        encoder.setName(ctx.OR2().getText());
        encoder.setAdr("reg");
        encoder.setField1(reg2Int(ctx.REG(0).getText()));
        encoder.setField2(reg2Int(ctx.REG(1).getText()));
        
        return null;
    }
    
    
    @Override
    public Void visitTypeR3(assemblerParser.TypeR3Context ctx)
    {
        encoder.setName(ctx.RI3().getText());
        encoder.setAdr("reg");
        encoder.setField1(reg2Int(ctx.REG(0).getText()));
        encoder.setField2(reg2Int(ctx.REG(1).getText()));
        encoder.setField3(reg2Int(ctx.REG(2).getText()));
        
        return null;
    }
    
    
    @Override
    public Void visitTypeI3A(assemblerParser.TypeI3AContext ctx)
    {
        encoder.setName(ctx.RI3().getText());
        encoder.setAdr("imm");
        encoder.setField1(reg2Int(ctx.REG(0).getText()));
        encoder.setField2(reg2Int(ctx.REG(1).getText()));
        visit(ctx.expression());
        encoder.setField3((int) value);
        
        return null;
    }
    
    
    @Override
    public Void visitTypeI3B(assemblerParser.TypeI3BContext ctx)
    {
        encoder.setName(ctx.OI3().getText());
        encoder.setAdr("imm");
        encoder.setField1(reg2Int(ctx.REG(0).getText()));
        encoder.setField2(reg2Int(ctx.REG(1).getText()));
        visit(ctx.expression());
        encoder.setField3((int) value);
        
        return null;
    }
    
    
    @Override
    public Void visitTypeR4(assemblerParser.TypeR4Context ctx)
    {
        encoder.setName(ctx.OR4().getText());
        encoder.setAdr("reg");
        encoder.setField1(reg2Int(ctx.REG(0).getText()));
        encoder.setField2(reg2Int(ctx.REG(1).getText()));
        encoder.setField3(reg2Int(ctx.REG(2).getText()));
        encoder.setField4(reg2Int(ctx.REG(3).getText()));
        
        return null;
    }
    
    
    @Override
    public Void visitTypeBRRGI(assemblerParser.TypeBRRGIContext ctx)
    {
        encoder.setName(ctx.BR().getText());
        encoder.setAdr("rgi");
        encoder.setField1(reg2Int(ctx.REG().getText()));
        if(ctx.COND() != null) // unconditional by default
            encoder.setField2(cond2Int(ctx.COND().getText()));

        return null;
    }
    
    
    @Override
    public Void visitTypeBRRGO(assemblerParser.TypeBRRGOContext ctx)
    {
        encoder.setName(ctx.BR().getText());
        encoder.setAdr("rgo");
        encoder.setField1(reg2Int(ctx.REG().getText()));
        visit(ctx.imm());
        encoder.setField2((int) value);
        if(ctx.COND() != null) // unconditional by default
            encoder.setField3(cond2Int(ctx.COND().getText()));
        
        return null;
    }
    
    
    @Override
    public Void visitTypeBRPCR(assemblerParser.TypeBRPCRContext ctx)
    {
        encoder.setName(ctx.BR().getText());
        encoder.setAdr("pcr");
        visit(ctx.expression());
        encoder.setField1((int) (value - (currentAddress + 4)));
        if(ctx.COND() != null) // unconditional by default
            encoder.setField2(cond2Int(ctx.COND().getText()));
        
        return null;
    }
    
    
    @Override
    public Void visitTypeLDRGI(assemblerParser.TypeLDRGIContext ctx)
    {
        encoder.setName(ctx.LD().getText());
        encoder.setAdr("rgi");
        encoder.setField1(reg2Int(ctx.REG(0).getText()));
        encoder.setField2(reg2Int(ctx.REG(1).getText()));
        encoder.setSize(ctx.MTYPE().getText());
        
        return null;
    }
    
    
    @Override
    public Void visitTypeLDRGO(assemblerParser.TypeLDRGOContext ctx)
    {
        encoder.setName(ctx.LD().getText());
        if(ctx.INCTYPE() != null)
        {
            if(ctx.INCTYPE().getText().equals("pre"))
                encoder.setAdr("rpr");
            else if(ctx.INCTYPE().getText().equals("post"))
                encoder.setAdr("rpo");
        }
        else
            encoder.setAdr("rgo");
        
        encoder.setField1(reg2Int(ctx.REG(0).getText()));
        encoder.setField2(reg2Int(ctx.REG(1).getText()));
        visit(ctx.imm());
        encoder.setField3((int) value);
        encoder.setSize(ctx.MTYPE().getText());
        
        return null;
    }
    
    
    @Override
    public Void visitTypeLDPCR(assemblerParser.TypeLDPCRContext ctx)
    {
        encoder.setName(ctx.LD().getText());
        encoder.setAdr("pcr");
        encoder.setField1(reg2Int(ctx.REG().getText()));
        visit(ctx.expression());
        encoder.setField2((int) (value - (currentAddress + 4)));
        encoder.setSize(ctx.MTYPE().getText());
        
        return null;
    }
    
    
    @Override
    public Void visitTypeSTRGI(assemblerParser.TypeSTRGIContext ctx)
    {
        encoder.setName(ctx.ST().getText());
        encoder.setAdr("rgi");
        encoder.setField1(reg2Int(ctx.REG(0).getText()));
        encoder.setField2(reg2Int(ctx.REG(1).getText()));
        encoder.setSize(ctx.MTYPE().getText());
        
        return null;
    }
    
    
    @Override
    public Void visitTypeSTRGO(assemblerParser.TypeSTRGOContext ctx)
    {
        encoder.setName(ctx.ST().getText());
        if(ctx.INCTYPE() != null)
        {
            if(ctx.INCTYPE().getText().equals("pre"))
                encoder.setAdr("rpr");
            else if(ctx.INCTYPE().getText().equals("post"))
                encoder.setAdr("rpo");
        }
        else
            encoder.setAdr("rgo");
        
        encoder.setField1(reg2Int(ctx.REG(0).getText()));
        visit(ctx.imm());
        encoder.setField2((int) value);
        encoder.setField3(reg2Int(ctx.REG(1).getText()));
        encoder.setSize(ctx.MTYPE().getText());
        
        return null;
    }
    
    
    @Override
    public Void visitTypeSTPCR(assemblerParser.TypeSTPCRContext ctx)
    {
        encoder.setName(ctx.ST().getText());
        encoder.setAdr("pcr");
        visit(ctx.expression());
        encoder.setField1((int) (value - (currentAddress + 4)));
        encoder.setField2(reg2Int(ctx.REG().getText()));
        encoder.setSize(ctx.MTYPE().getText());
        
        return null;
    }
    
    
    public int reg2Int(String name)
    {
        int val = 0;
        
        switch(name)
        {
            case "r0":  case "v0":  val = 0;  break; // return value 0
            case "r1":  case "v1":  val = 1;  break; // return value 1
            case "r2":  case "a0":  val = 2;  break; // argument value 0
            case "r3":  case "a1":  val = 3;  break; // argument value 1
            case "r4":  case "a2":  val = 4;  break; // argument value 2
            case "r5":  case "a3":  val = 5;  break; // argument value 3
            case "r6":  case "a4":  val = 6;  break; // argument value 4
            case "r7":  case "a5":  val = 7;  break; // argument value 5
            case "r8":  case "a6":  val = 8;  break; // argument value 6
            case "r9":  case "a7":  val = 9;  break; // argument value 7
            case "r10": case "t0":  val = 10; break; // temporary value 0
            case "r11": case "t1":  val = 11; break; // temporary value 1
            case "r12": case "t2":  val = 12; break; // temporary value 2
            case "r13": case "t3":  val = 13; break; // temporary value 3
            case "r14": case "t4":  val = 14; break; // temporary value 4
            case "r15": case "t5":  val = 15; break; // temporary value 5
            case "r16": case "t6":  val = 16; break; // temporary value 6
            case "r17": case "t7":  val = 17; break; // temporary value 7
            case "r18": case "t8":  val = 18; break; // temporary value 8
            case "r19": case "t9":  val = 19; break; // temporary value 9
            case "r20": case "t10": val = 20; break; // temporary value 10
            case "r21": case "t11": val = 21; break; // temporary value 11
            case "r22": case "t12": val = 22; break; // temporary value 12
            case "r23": case "t13": val = 23; break; // temporary value 13
            case "r24": case "t14": val = 24; break; // temporary value 14
            case "r25": case "t15": val = 25; break; // temporary value 15
            case "r26": case "t16": val = 26; break; // temporary value 16
            case "r27": case "t17": val = 27; break; // temporary value 17
            case "r28": case "fp":  val = 28; break; // frame pointer
            case "r29": case "ea":  val = 29; break; // exception address
            case "r30": case "ra":  val = 30; break; // return address
            case "r31": case "sp":  val = 31; break; // stack pointer
        }
        
        return val;
    }
    
    
    public int sysReg2Int(String name)
    {
        int val = 0;
        
        switch(name)
        {
            case "sys0":  val = 0;  break;
            case "sys1":  val = 1;  break;
            case "sys2":  val = 2;  break;
            case "sys3":  val = 3;  break;
            case "sys4":  val = 4;  break;
            case "sys5":  val = 5;  break;
            case "sys6":  val = 6;  break;
            case "sys7":  val = 7;  break;
            case "sys8":  val = 8;  break;
            case "sys9":  val = 9;  break;
            case "sys10": val = 10; break;
            case "sys11": val = 11; break;
            case "sys12": val = 12; break;
            case "sys13": val = 13; break;
            case "sys14": val = 14; break;
            case "sys15": val = 15; break;
            case "sys16": val = 16; break;
            case "sys17": val = 17; break;
            case "sys18": val = 18; break;
            case "sys19": val = 19; break;
            case "sys20": val = 20; break;
            case "sys21": val = 21; break;
            case "sys22": val = 22; break;
            case "sys23": val = 23; break;
            case "sys24": val = 24; break;
            case "sys25": val = 25; break;
            case "sys26": val = 26; break;
            case "sys27": val = 27; break;
            case "sys28": val = 28; break;
            case "sys29": val = 29; break;
            case "sys30": val = 30; break;
            case "sys31": val = 31; break;
        }
        
        return val;
    }
    
    
    public int cond2Int(String name)
    {
        int val;
        
        switch(name)
        {
            case "unc": val = 0;  break; // unconditional
            case "zr":  val = 1;  break; // zero
            case "==":  val = 1;  break; // equal
            case "!zr": val = 2;  break; // not zero
            case "!=":  val = 2;  break; // not equal
            case "cr":  val = 3;  break; // carry
            case ">=u": val = 3;  break; // greater than or equal (unsigned)
            case "!cr": val = 4;  break; // not carry
            case "<u":  val = 4;  break; // less than (unsigned)
            case "ov":  val = 5;  break; // overflow
            case "!ov": val = 6;  break; // not overflow
            case "ng":  val = 7;  break; // negative
            case "!ng": val = 8;  break; // not negative
            case ">u":  val = 9;  break; // greater than (unsigned)
            case "<=u": val = 10; break; // less than or equal (unsigned)
            case ">":   val = 11; break; // greater than (signed)
            case "<":   val = 12; break; // less than (signed)
            case ">=":  val = 13; break; // greater than or equal (signed)
            case "<=":  val = 14; break; // less than or equal (signed)
            default:    val = 0;  break;
        }
        
        return val;
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
