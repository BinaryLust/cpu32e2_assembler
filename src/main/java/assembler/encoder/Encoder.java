

package assembler.encoder;


public class Encoder
{
    private boolean showDebugInfo;
    
    private String  errorMessage;
    
    // instruction lookup table
    private InstTable table    = new InstTable();
    
    // instruction type
    private String name;
    private String size;
    private String adr;
    
    // field values
    private int    field1;
    private int    field2;
    private int    field3;
    private int    field4;
    
    // resulting encoded instruction
    private int    instruction;
    
    private  Field  opcodeField = new Field(new short[] {26, 27, 28, 29, 30, 31}, false); // bits[31:26]
    private  Field  rcodeField  = new Field(new short[] {0,  1,  2,  3,  4,  5},  false); // bits[5:0]
    private  Field  condField   = new Field(new short[] {6,  7,  8,  9}, false);          // bits[9:6]
    private  Field  drlField    = new Field(new short[] {21, 22, 23, 24, 25}, false);     // bits[25:21]
    private  Field  drhField    = new Field(new short[] {6,  7,  8,  9,  10}, false);     // bits[10:6]
    private  Field  sraField    = new Field(new short[] {16, 17, 18, 19, 20}, false);     // bits[20:16]
    private  Field  srbField    = new Field(new short[] {11, 12, 13, 14, 15}, false);     // bits[15:11]
    
    
    // bits[15:0] sign extended to bits[31:0]
    private  Field  imm16aField = new Field(new short[] {0, 1, 2,  3,  4,  5,  6,  7,
                                                        8, 9, 10, 11, 12, 13, 14, 15}, true);
    
    
    // {bits[25:21], bits[10:0]} sign extended to bits[31:0]
    private  Field  imm16bField = new Field(new short[] {0, 1, 2,  3,  4,  5,  6,  7,
                                                        8, 9, 10, 21, 22, 23, 24, 25}, true);
    
    
    // {bits[20:16], bits[10:0]} zero extended to bits[31:0]
    private  Field  imm16cField = new Field(new short[] {0, 1, 2,  3,  4,  5,  6,  7,
                                                        8, 9, 10, 16, 17, 18, 19, 20}, false);
    
    
    // {bits[25:21], bits[15:0]} sign extended to bits[31:0]
    private  Field  imm21aField = new Field(new short[] {0,  1,  2,  3,  4,  5,  6,  7,
                                                        8,  9,  10, 11, 12, 13, 14, 15,
                                                        21, 22, 23, 24, 25}, true);
    
    
    // bits[20:0] sign extended to bits[31:0]
    private  Field  imm21bField = new Field(new short[] {0,  1,  2,  3,  4,  5,  6,  7,
                                                        8,  9,  10, 11, 12, 13, 14, 15,
                                                        16, 17, 18, 19, 20}, true);
    
   
    // {bits[25:16], bits[10:0]} sign extended to bits[31:0]
    private  Field  imm21cField = new Field(new short[] {0,  1,  2,  3,  4,  5,  6,  7,
                                                        8,  9,  10, 16, 17, 18, 19, 20,
                                                        21, 22, 23, 24, 25}, true);
   
   
    // bits[10:6] zero extended to bits[31:0]
    private  Field  imm5Field   = new Field(new short[] {6, 7, 8, 9, 10}, false);
      
    
    // bits[5:0] zero extended to bits[31:0]
    private  Field  imm6Field   = new Field(new short[] {0, 1, 2, 3, 4, 5}, false);
   
   
    // {bits[25:21], bits[15:10], bits[5:0]} sign extended to bits[31:2] and bits[1:0] filled with zero's
    // we must shift this left 2 after decoding it to fill the trailing zero's in
    private  Field  imm19Field  = new Field(new short[] {0,  1,  2,  3,  4,  5,  10, 11,
                                                        12, 13, 14, 15, 21, 22, 23, 24,
                                                        25}, true);
      
    
    // {bits[25:10], bits[5:0]} sign extended to bits[31:2] and bits[1:0] filled with zero's
    // we must shift this left 2 after decoding it to fill the trailing zero's in
    private  Field  imm24Field  = new Field(new short[] {0,  1,  2,  3,  4,  5,  10, 11,
                                                        12, 13, 14, 15, 16, 17, 18, 19,
                                                        20, 21, 22, 23, 24, 25}, true);
    
    
    // constructors
    public Encoder(boolean showDebugInfo)
    {
        this.showDebugInfo = showDebugInfo;
    }
    
    
    public void setName(String newName) { name = newName; }
    public void setSize(String newSize) { size = newSize; }
    public void setAdr(String newAdr)   { adr = newAdr; }
    public void setField1(int value) { field1 = value; }
    public void setField2(int value) { field2 = value; }
    public void setField3(int value) { field3 = value; }
    public void setField4(int value) { field4 = value; }
    
    public void setInstruction(int value) { instruction = value; }
    public int  getInstruction()          { return instruction;  }
    
    public String getErrorMessage() { return errorMessage; }
    
    
    public boolean encodeInstruction()
    {
        instruction = 0;
        
        boolean error = false;
        errorMessage = "";
        
        if(table.setEntry(name, size, adr))
        {
            if(showDebugInfo)
            {
                System.out.println("Opcode: " + table.getOpcode() + "  Rcode: " + table.getRcode() +
                                   "  Dest1: " + table.getField1() + "  Value1: " + field1 +
                                   "  Dest2: " + table.getField2() + "  Value2: " + field2 +
                                   "  Dest3: " + table.getField3() + "  Value3: " + field3 +
                                   "  Dest4: " + table.getField4() + "  Value4: " + field4);
            }
            
            int opcode = table.getOpcode();
            int rcode  = table.getRcode();
            
            // encode the instruction
            if(!encodeField(FldConst.OPCODE, opcode))
            {
                errorMessage += "Opcode out of range! ";
                error = true;
            }
            
            if(rcode != -1)
                if(!encodeField(FldConst.RCODE, rcode))
                {
                    errorMessage += "Rcode out of range! ";
                    error = true;
                }
            
            if(!encodeField(table.getField1(), field1))
            {
                errorMessage += "Field1 out of range! ";
                error = true;
            }
        
            if(!encodeField(table.getField2(), field2))
            {
                errorMessage += "Field2 out of range! ";
                error = true;
            }
        
            if(!encodeField(table.getField3(), field3))
            {
                errorMessage += "Field3 out of range! ";
                error = true;
            }
    
            if(!encodeField(table.getField4(), field4))
            {
                errorMessage += "Field4 out of range! ";
                error = true;
            }

            return !error;
        }
        else
        {
            errorMessage = "instruction table look up error";
            return false;
        }
    }
    
    
    public boolean encodeField(FldConst f, int value)
    {
        if(checkRange(f, value))
        {
            switch(f)
            {
                case XXX:    break;
                case OPCODE: instruction = opcodeField.setValue(instruction, value); break;
                case RCODE:  instruction = rcodeField.setValue(instruction, value);  break;
                case COND:   instruction = condField.setValue(instruction, value);   break;
                case DRL:    instruction = drlField.setValue(instruction, value);    break;
                case DRH:    instruction = drhField.setValue(instruction, value);    break;
                case SRA:    instruction = sraField.setValue(instruction, value);    break;
                case SRB:    instruction = srbField.setValue(instruction, value);    break;
                case IMM16A: instruction = imm16aField.setValue(instruction, value); break;
                case IMM16B: instruction = imm16bField.setValue(instruction, value); break;
                case IMM16C: instruction = imm16cField.setValue(instruction, value); break;
                case IMM21A: instruction = imm21aField.setValue(instruction, value); break;
                case IMM21B: instruction = imm21bField.setValue(instruction, value); break;
                case IMM21C: instruction = imm21cField.setValue(instruction, value); break;
                case IMM5:   instruction = imm5Field.setValue(instruction, value);   break;
                case IMM6:   instruction = imm6Field.setValue(instruction, value);   break;
                case IMM19:  instruction = imm19Field.setValue(instruction, value >>> 2);  break;
                case IMM24:  instruction = imm24Field.setValue(instruction, value >>> 2);  break;
            }
            
            return true;
        }
        else
        {
            return false;
        }
    }
    
    
    public boolean checkRange(FldConst f, int value)
    {
        boolean valid = true;
        
        switch(f)
        {
            case XXX:    valid = true;                                        break;
            case OPCODE: valid = ((value >= 0) && (value <= 63));             break; // min = 0,          max = 63
            case RCODE:  valid = ((value >= 0) && (value <= 63));             break; // min = 0,          max = 63
            case COND:   valid = ((value >= 0) && (value <= 15));             break; // min = 0,          max = 15
            case DRL:    valid = ((value >= 0) && (value <= 31));             break; // min = 0,          max = 31
            case DRH:    valid = ((value >= 0) && (value <= 31));             break; // min = 0,          max = 31
            case SRA:    valid = ((value >= 0) && (value <= 31));             break; // min = 0,          max = 31
            case SRB:    valid = ((value >= 0) && (value <= 31));             break; // min = 0,          max = 31
            case IMM16A: valid = ((value >= -32768) && (value <= 32767));     break; // min = -32,768,    max = 32,767
            case IMM16B: valid = ((value >= -32768) && (value <= 32767));     break; // min = -32,768,    max = 32,767
            case IMM16C: valid = ((value >= -32768) && (value <= 65535));     break; // min = -32,768,    max = 65,535
            case IMM21A: valid = ((value >= -1048576) && (value <= 1048575)); break; // min = -1,048,576, max = 1,048,575
            case IMM21B: valid = ((value >= -1048576) && (value <= 1048575)); break; // min = -1,048,576, max = 1,048,575
            case IMM21C: valid = ((value >= -1048576) && (value <= 1048575)); break; // min = -1,048,576, max = 1,048,575
            case IMM5:   valid = ((value >= 0) && (value <= 31));             break; // min = 0,          max = 31
            case IMM6:   valid = ((value >= 0) && (value <= 63));             break; // min = 0,          max = 63
            case IMM19:  valid = ((value >= -262144) && (value <= 262143));   break; // min = -262,144,   max = 262,143   // should be a multiple of 4 also
            case IMM24:  valid = ((value >= -8388608) && (value <= 8388607)); break; // min = -8,388,608, max = 8,388,607 // should be a multiple of 4 also
        }
        
        return valid;
    }
}

