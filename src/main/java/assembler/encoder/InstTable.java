

package assembler.encoder;

// converts the token column in the parse tree to the field it relates to in output instruction

import java.util.HashMap;
import java.util.Map;

public class InstTable {
    
    public Map<String, InstEntry> table;
    public InstEntry              currentEntry;


    public InstTable()
    {
        table = new HashMap<String, InstEntry>() {{
            put("adc"   + "xxx"   + "reg", new InstEntry(0,	0,	FldConst.DRL,	 FldConst.SRA,	  FldConst.SRB,	   FldConst.XXX));
            put("add"   + "xxx"   + "reg", new InstEntry(0,	1,	FldConst.DRL,	 FldConst.SRA,	  FldConst.SRB,	   FldConst.XXX));
            put("and"   + "xxx"   + "reg", new InstEntry(0,	2,	FldConst.DRL,	 FldConst.SRA,	  FldConst.SRB,	   FldConst.XXX));
            put("br"    + "xxx"   + "rgi", new InstEntry(0,	3,	FldConst.SRA,	 FldConst.COND,	  FldConst.XXX,	   FldConst.XXX));
            put("break" + "xxx"   + "xxx", new InstEntry(0,	4,	FldConst.XXX,	 FldConst.XXX,	  FldConst.XXX,	   FldConst.XXX));
            put("brl"   + "xxx"   + "rgi", new InstEntry(0,	5,	FldConst.SRA,	 FldConst.COND,	  FldConst.XXX,	   FldConst.XXX));
            put("cmp"   + "xxx"   + "reg", new InstEntry(0,	6,	FldConst.SRA,	 FldConst.SRB,	  FldConst.XXX,	   FldConst.XXX));
            put("iret"  + "xxx"   + "rgi", new InstEntry(0,	7,	FldConst.SRA,	 FldConst.XXX,	  FldConst.XXX,	   FldConst.XXX));
            put("ld"    + "sbyte" + "rgi", new InstEntry(0,	8,	FldConst.DRL,	 FldConst.SRA,	  FldConst.XXX,	   FldConst.XXX));
            put("ld"    + "ubyte" + "rgi", new InstEntry(0,	9,	FldConst.DRL,	 FldConst.SRA,	  FldConst.XXX,	   FldConst.XXX));
            put("ld"    + "dword" + "rgi", new InstEntry(0,	10,	FldConst.DRL,	 FldConst.SRA,	  FldConst.XXX,	   FldConst.XXX));
            put("ld"    + "sword" + "rgi", new InstEntry(0,	11,	FldConst.DRL,	 FldConst.SRA,	  FldConst.XXX,	   FldConst.XXX));
            put("ld"    + "uword" + "rgi", new InstEntry(0,	12,	FldConst.DRL,	 FldConst.SRA,	  FldConst.XXX,	   FldConst.XXX));
            put("mov"   + "xxx"   + "reg", new InstEntry(0,	13,	FldConst.DRL,	 FldConst.SRB,	  FldConst.XXX,	   FldConst.XXX));
            put("nop"   + "xxx"   + "xxx", new InstEntry(0,	14,	FldConst.XXX,	 FldConst.XXX,	  FldConst.XXX,	   FldConst.XXX));
            put("not"   + "xxx"   + "reg", new InstEntry(0,	15,	FldConst.DRL,	 FldConst.SRB,	  FldConst.XXX,	   FldConst.XXX));
            put("or"    + "xxx"   + "reg", new InstEntry(0,	16,	FldConst.DRL,	 FldConst.SRA,	  FldConst.SRB,	   FldConst.XXX));
            put("rcl"   + "xxx"   + "imm", new InstEntry(0,	17,	FldConst.DRL,	 FldConst.SRA,	  FldConst.IMM5,   FldConst.XXX));
            put("rcl"   + "xxx"   + "reg", new InstEntry(0,	18,	FldConst.DRL,	 FldConst.SRA,	  FldConst.SRB,	   FldConst.XXX));
            put("rcr"   + "xxx"   + "imm", new InstEntry(0,	19,	FldConst.DRL,	 FldConst.SRA,	  FldConst.IMM5,   FldConst.XXX));
            put("rcr"   + "xxx"   + "reg", new InstEntry(0,	20,	FldConst.DRL,	 FldConst.SRA,	  FldConst.SRB,	   FldConst.XXX));
            put("rol"   + "xxx"   + "imm", new InstEntry(0,	21,	FldConst.DRL,	 FldConst.SRA,	  FldConst.IMM5,   FldConst.XXX));
            put("rol"   + "xxx"   + "reg", new InstEntry(0,	22,	FldConst.DRL,	 FldConst.SRA,	  FldConst.SRB,	   FldConst.XXX));
            put("ror"   + "xxx"   + "imm", new InstEntry(0,	23,	FldConst.DRL,	 FldConst.SRA,	  FldConst.IMM5,   FldConst.XXX));
            put("ror"   + "xxx"   + "reg", new InstEntry(0,	24,	FldConst.DRL,	 FldConst.SRA,	  FldConst.SRB,	   FldConst.XXX));
            put("lsr"   + "xxx"   + "reg", new InstEntry(0,	25,	FldConst.DRL,	 FldConst.SRB,	  FldConst.XXX,	   FldConst.XXX));
            put("sar"   + "xxx"   + "imm", new InstEntry(0,	26,	FldConst.DRL,	 FldConst.SRA,	  FldConst.IMM5,   FldConst.XXX));
            put("sar"   + "xxx"   + "reg", new InstEntry(0,	27,	FldConst.DRL,	 FldConst.SRA,	  FldConst.SRB,	   FldConst.XXX));
            put("sbb"   + "xxx"   + "reg", new InstEntry(0,	28,	FldConst.DRL,	 FldConst.SRA,	  FldConst.SRB,	   FldConst.XXX));
            put("sdiv"  + "xxx"   + "reg", new InstEntry(0,	29,	FldConst.DRH,	 FldConst.DRL,	  FldConst.SRA,	   FldConst.SRB));
            put("shl"   + "xxx"   + "imm", new InstEntry(0,	30,	FldConst.DRL,	 FldConst.SRA,	  FldConst.IMM5,   FldConst.XXX));
            put("shl"   + "xxx"   + "reg", new InstEntry(0,	31,	FldConst.DRL,	 FldConst.SRA,	  FldConst.SRB,	   FldConst.XXX));
            put("shr"   + "xxx"   + "imm", new InstEntry(0,	32,	FldConst.DRL,	 FldConst.SRA,	  FldConst.IMM5,   FldConst.XXX));
            put("shr"   + "xxx"   + "reg", new InstEntry(0,	33,	FldConst.DRL,	 FldConst.SRA,	  FldConst.SRB,	   FldConst.XXX));
            put("smul"  + "xxx"   + "reg", new InstEntry(0,	34,	FldConst.DRH,	 FldConst.DRL,	  FldConst.SRA,	   FldConst.SRB));
            put("ssr"   + "xxx"   + "reg", new InstEntry(0,	35,	FldConst.DRL,    FldConst.SRB,	  FldConst.XXX,	   FldConst.XXX));
            put("st"    + "byte"  + "rgi", new InstEntry(0,	36,	FldConst.SRA,	 FldConst.SRB,	  FldConst.XXX,	   FldConst.XXX));
            put("st"    + "dword" + "rgi", new InstEntry(0,	37,	FldConst.SRA,	 FldConst.SRB,	  FldConst.XXX,	   FldConst.XXX));
            put("st"    + "word"  + "rgi", new InstEntry(0,	38,	FldConst.SRA,	 FldConst.SRB,	  FldConst.XXX,	   FldConst.XXX));
            put("sub"   + "xxx"   + "reg", new InstEntry(0,	39,	FldConst.DRL,	 FldConst.SRA,	  FldConst.SRB,	   FldConst.XXX));
            put("teq"   + "xxx"   + "reg", new InstEntry(0,	40,	FldConst.SRA,	 FldConst.SRB,	  FldConst.XXX,	   FldConst.XXX));
            put("tst"   + "xxx"   + "reg", new InstEntry(0,	41,	FldConst.SRA,	 FldConst.SRB,	  FldConst.XXX,	   FldConst.XXX));
            put("uadc"  + "xxx"   + "reg", new InstEntry(0,	42,	FldConst.DRL,	 FldConst.SRA,	  FldConst.SRB,	   FldConst.XXX));
            put("uadd"  + "xxx"   + "reg", new InstEntry(0,	43,	FldConst.DRL,	 FldConst.SRA,	  FldConst.SRB,	   FldConst.XXX));
            put("ucmp"  + "xxx"   + "reg", new InstEntry(0,	44,	FldConst.SRA,	 FldConst.SRB,	  FldConst.XXX,	   FldConst.XXX));
            put("udiv"  + "xxx"   + "reg", new InstEntry(0,	45,	FldConst.DRH,	 FldConst.DRL,	  FldConst.SRA,	   FldConst.SRB));
            put("umul"  + "xxx"   + "reg", new InstEntry(0,	46,	FldConst.DRH,	 FldConst.DRL,	  FldConst.SRA,	   FldConst.SRB));
            put("usbb"  + "xxx"   + "reg", new InstEntry(0,	47,	FldConst.DRL,	 FldConst.SRA,	  FldConst.SRB,	   FldConst.XXX));
            put("usub"  + "xxx"   + "reg", new InstEntry(0,	48,	FldConst.DRL,	 FldConst.SRA,	  FldConst.SRB,	   FldConst.XXX));
            put("xor"   + "xxx"   + "reg", new InstEntry(0,	49,	FldConst.DRL,	 FldConst.SRA,	  FldConst.SRB,	   FldConst.XXX));
            put("adc"   + "xxx"   + "imm", new InstEntry(1,	-1,	FldConst.DRL,	 FldConst.SRA,	  FldConst.IMM16A, FldConst.XXX));
            put("add"   + "xxx"   + "imm", new InstEntry(2,	-1,	FldConst.DRL,	 FldConst.SRA,	  FldConst.IMM16A, FldConst.XXX));
            put("and"   + "xxx"   + "imm", new InstEntry(3,	-1,	FldConst.DRL,	 FldConst.SRA,	  FldConst.IMM16A, FldConst.XXX));
            put("br"    + "xxx"   + "pcr", new InstEntry(4,	-1,	FldConst.IMM24,	 FldConst.COND,	  FldConst.XXX,	   FldConst.XXX));
            put("br"    + "xxx"   + "rgo", new InstEntry(5,	-1,	FldConst.SRA,	 FldConst.IMM19,  FldConst.COND,   FldConst.XXX));
            put("brl"   + "xxx"   + "pcr", new InstEntry(6,	-1,	FldConst.IMM24,	 FldConst.COND,	  FldConst.XXX,	   FldConst.XXX));
            put("brl"   + "xxx"   + "rgo", new InstEntry(7,	-1,	FldConst.SRA,	 FldConst.IMM19,  FldConst.COND,   FldConst.XXX));
            put("cmp"   + "xxx"   + "imm", new InstEntry(8,	-1,	FldConst.SRA,	 FldConst.IMM21A, FldConst.XXX,    FldConst.XXX));
            put("int"   + "xxx"   + "imm", new InstEntry(9,	-1,	FldConst.IMM6,   FldConst.XXX,	  FldConst.XXX,	   FldConst.XXX));
            put("ld"    + "sbyte" + "pcr", new InstEntry(10,	-1,	FldConst.DRL,	 FldConst.IMM21B, FldConst.XXX,	   FldConst.XXX));
            put("ld"    + "sbyte" + "rgo", new InstEntry(11,	-1,	FldConst.DRL,	 FldConst.SRA,	  FldConst.IMM16A, FldConst.XXX));
            put("ld"    + "sbyte" + "rpo", new InstEntry(12,	-1,	FldConst.DRL,	 FldConst.SRA,	  FldConst.IMM16A, FldConst.XXX));
            put("ld"    + "sbyte" + "rpr", new InstEntry(13,	-1,	FldConst.DRL,	 FldConst.SRA,	  FldConst.IMM16A, FldConst.XXX));
            put("ld"    + "ubyte" + "pcr", new InstEntry(14,	-1,	FldConst.DRL,	 FldConst.IMM21B, FldConst.XXX,	   FldConst.XXX));
            put("ld"    + "ubyte" + "rgo", new InstEntry(15,	-1,	FldConst.DRL,	 FldConst.SRA,	  FldConst.IMM16A, FldConst.XXX));
            put("ld"    + "ubyte" + "rpo", new InstEntry(16,	-1,	FldConst.DRL,	 FldConst.SRA,	  FldConst.IMM16A, FldConst.XXX));
            put("ld"    + "ubyte" + "rpr", new InstEntry(17,	-1,	FldConst.DRL,	 FldConst.SRA,	  FldConst.IMM16A, FldConst.XXX));
            put("ld"    + "dword" + "pcr", new InstEntry(18,	-1,	FldConst.DRL,	 FldConst.IMM21B, FldConst.XXX,	   FldConst.XXX));
            put("ld"    + "dword" + "rgo", new InstEntry(19,	-1,	FldConst.DRL,	 FldConst.SRA,	  FldConst.IMM16A, FldConst.XXX));
            put("ld"    + "dword" + "rpo", new InstEntry(20,	-1,	FldConst.DRL,	 FldConst.SRA,	  FldConst.IMM16A, FldConst.XXX));
            put("ld"    + "dword" + "rpr", new InstEntry(21,	-1,	FldConst.DRL,	 FldConst.SRA,	  FldConst.IMM16A, FldConst.XXX));
            put("ld"    + "sword" + "pcr", new InstEntry(22,	-1,	FldConst.DRL,	 FldConst.IMM21B, FldConst.XXX,	   FldConst.XXX));
            put("ld"    + "sword" + "rgo", new InstEntry(23,	-1,	FldConst.DRL,	 FldConst.SRA,	  FldConst.IMM16A, FldConst.XXX));
            put("ld"    + "sword" + "rpo", new InstEntry(24,	-1,	FldConst.DRL,	 FldConst.SRA,	  FldConst.IMM16A, FldConst.XXX));
            put("ld"    + "sword" + "rpr", new InstEntry(25,	-1,	FldConst.DRL,	 FldConst.SRA,	  FldConst.IMM16A, FldConst.XXX));
            put("ld"    + "uword" + "pcr", new InstEntry(26,	-1,	FldConst.DRL,	 FldConst.IMM21B, FldConst.XXX,	   FldConst.XXX));
            put("ld"    + "uword" + "rgo", new InstEntry(27,	-1,	FldConst.DRL,	 FldConst.SRA,	  FldConst.IMM16A, FldConst.XXX));
            put("ld"    + "uword" + "rpo", new InstEntry(28,	-1,	FldConst.DRL,	 FldConst.SRA,	  FldConst.IMM16A, FldConst.XXX));
            put("ld"    + "uword" + "rpr", new InstEntry(29,	-1,	FldConst.DRL,	 FldConst.SRA,	  FldConst.IMM16A, FldConst.XXX));
            put("mov"   + "xxx"   + "imm", new InstEntry(30,	-1,	FldConst.DRL,	 FldConst.IMM21B, FldConst.XXX,	   FldConst.XXX));
            put("mui"   + "xxx"   + "imm", new InstEntry(31,	-1,	FldConst.DRL,	 FldConst.SRB,    FldConst.IMM16C, FldConst.XXX));
            put("or"    + "xxx"   + "imm", new InstEntry(32,	-1,	FldConst.DRL,	 FldConst.SRA,	  FldConst.IMM16A, FldConst.XXX));
            put("sbb"   + "xxx"   + "imm", new InstEntry(33,	-1,	FldConst.DRL,	 FldConst.SRA,	  FldConst.IMM16A, FldConst.XXX));
            put("st"    + "byte"  + "pcr", new InstEntry(34,	-1,	FldConst.IMM21C, FldConst.SRB,	  FldConst.XXX,	   FldConst.XXX));
            put("st"    + "byte"  + "rgo", new InstEntry(35,	-1,	FldConst.SRA,	 FldConst.IMM16B, FldConst.SRB,	   FldConst.XXX));
            put("st"    + "byte"  + "rpo", new InstEntry(36,	-1,	FldConst.SRA,	 FldConst.IMM16B, FldConst.SRB,	   FldConst.XXX));
            put("st"    + "byte"  + "rpr", new InstEntry(37,	-1,	FldConst.SRA,	 FldConst.IMM16B, FldConst.SRB,	   FldConst.XXX));
            put("st"    + "dword" + "pcr", new InstEntry(38,	-1,	FldConst.IMM21C, FldConst.SRB,	  FldConst.XXX,	   FldConst.XXX));
            put("st"    + "dword" + "rgo", new InstEntry(39,	-1,	FldConst.SRA,	 FldConst.IMM16B, FldConst.SRB,	   FldConst.XXX));
            put("st"    + "dword" + "rpo", new InstEntry(40,	-1,	FldConst.SRA,	 FldConst.IMM16B, FldConst.SRB,	   FldConst.XXX));
            put("st"    + "dword" + "rpr", new InstEntry(41,	-1,	FldConst.SRA,	 FldConst.IMM16B, FldConst.SRB,	   FldConst.XXX));
            put("st"    + "word"  + "pcr", new InstEntry(42,	-1,	FldConst.IMM21C, FldConst.SRB,	  FldConst.XXX,	   FldConst.XXX));
            put("st"    + "word"  + "rgo", new InstEntry(43,	-1,	FldConst.SRA,	 FldConst.IMM16B, FldConst.SRB,	   FldConst.XXX));
            put("st"    + "word"  + "rpo", new InstEntry(44,	-1,	FldConst.SRA,	 FldConst.IMM16B, FldConst.SRB,	   FldConst.XXX));
            put("st"    + "word"  + "rpr", new InstEntry(45,	-1,	FldConst.SRA,	 FldConst.IMM16B, FldConst.SRB,	   FldConst.XXX));
            put("sub"   + "xxx"   + "imm", new InstEntry(46,	-1,	FldConst.DRL,	 FldConst.SRA,	  FldConst.IMM16A, FldConst.XXX));
            put("teq"   + "xxx"   + "imm", new InstEntry(47,	-1,	FldConst.SRA,	 FldConst.IMM21A, FldConst.XXX,    FldConst.XXX));
            put("tst"   + "xxx"   + "imm", new InstEntry(48,	-1,	FldConst.SRA,	 FldConst.IMM21A, FldConst.XXX,    FldConst.XXX));
            put("uadc"  + "xxx"   + "imm", new InstEntry(49,	-1,	FldConst.DRL,	 FldConst.SRA,	  FldConst.IMM16A, FldConst.XXX));
            put("uadd"  + "xxx"   + "imm", new InstEntry(50,	-1,	FldConst.DRL,	 FldConst.SRA,	  FldConst.IMM16A, FldConst.XXX));
            put("ucmp"  + "xxx"   + "imm", new InstEntry(51,	-1,	FldConst.SRA,	 FldConst.IMM21A, FldConst.XXX,    FldConst.XXX));
            put("usbb"  + "xxx"   + "imm", new InstEntry(52,	-1,	FldConst.DRL,	 FldConst.SRA,	  FldConst.IMM16A, FldConst.XXX));
            put("usub"  + "xxx"   + "imm", new InstEntry(53,	-1,	FldConst.DRL,	 FldConst.SRA,	  FldConst.IMM16A, FldConst.XXX));
            put("xor"   + "xxx"   + "imm", new InstEntry(54,	-1,	FldConst.DRL,	 FldConst.SRA,	  FldConst.IMM16A, FldConst.XXX));
        }};
    }
    
    
    public boolean setEntry(String name, String size, String adr)
    {
        currentEntry = table.get(name + size + adr);
        
        if(currentEntry != null)
            return true;
        else
            return false;
    }
    
    
    public int getOpcode()
    {
        return currentEntry.opcode;
    }
    
    
    public int getRcode()
    {
        return currentEntry.rcode;
    }
    
    
    public FldConst getField1()
    {
        return currentEntry.field1;
    }
    
    
    public FldConst getField2()
    {
        return currentEntry.field2;
    }
        
        
    public FldConst getField3()
    {
        return currentEntry.field3;
    }
            
            
    public FldConst getField4()
    {
        return currentEntry.field4;
    }
}

