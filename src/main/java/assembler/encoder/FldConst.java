

package assembler.encoder;


// field destination
public enum FldConst
{
    XXX,    // min = xxx,        max = xxx
    OPCODE, // min = 0,          max = 63
    RCODE,  // min = 0,          max = 63
    COND,   // min = 0,          max = 15
    DRL,    // min = 0,          max = 31
    DRH,    // min = 0,          max = 31
    SRA,    // min = 0,          max = 31
    SRB,    // min = 0,          max = 31
    IMM16A, // min = -32,768,    max = 32,767
    IMM16B, // min = -32,768,    max = 32,767
    IMM16C, // min = -32,768,    max = 65,535
    IMM21A, // min = -1,048,576, max = 1,048,575
    IMM21B, // min = -1,048,576, max = 1,048,575
    IMM21C, // min = -1,048,576, max = 1,048,575
    IMM5,   // min = 0,          max = 31
    IMM6,   // min = 0,          max = 63
    IMM19,  // min = -262,144,   max = 262,143   // should be a multiple of 4 also
    IMM24   // min = -8,388,608, max = 8,388,607 // should be a multiple of 4 also
}

