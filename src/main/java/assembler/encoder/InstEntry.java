

package assembler.encoder;


public class InstEntry
{
    //public String    name;   // string name of instruction
    //public adrConst  mode;   // addressing mode
    //public sizeConst size;   // memory operation size
    public int       opcode; // opcode
    public int       rcode;  // rcode
    public FldConst  field1; // what to encode for field 1
    public FldConst  field2; // what to encode for field 2
    public FldConst  field3; // what to encode for field 3
    public FldConst  field4; // what to encode for field 4
    
    
    //instEntry(String name, adrConst mode, sizeConst size, int opcode, int rcode, fldConst field1, fldConst field2, fldConst field3, fldConst field4)
    public InstEntry(int opcode, int rcode, FldConst field1, FldConst field2, FldConst field3, FldConst field4)
    {
        //this.name = name;
        //this.mode = mode;
        //this.size = size;
        this.opcode = opcode;
        this.rcode  = rcode;
        this.field1 = field1;
        this.field2 = field2;
        this.field3 = field3;
        this.field4 = field4;
    }
}
