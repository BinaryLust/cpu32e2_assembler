/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// data written outside of a data block will go to the code segment inline with the instructions
// data written inside of a data block will go to the data segment

// we need more passes
// pass 0: get macro definitions and expand code where macros have been used
// pass 1: calculate code block label addresses, and save scope levels (maybe we don't want all labels to be local scope?)
// pass 2: calculate data block label addresses, and save scope levels
// pass 3: calculate constant block values, save scope levels
// pass 4: generate code and data, output machine code

grammar assembler;

// done below here //////////////////////////////////////////////////////////////////////////////////////////////////
input         : block+ ;

block         : (functionBlock | dataBlock | constBlock | masterLine+) ;

codeLabel     : LABEL ;

masterLine    : codeLabel? (inst | dataDecl | origin | align)? end ;

end           : NEWLINE
              | FILEEND
              ;

value         : (DNUM | HNUM | BNUM | CHAR | ID) ;

// expression
expression    : op=('+'|'-'|'~') atom                      # Unary
              | expression op=('*'|'/'|'%') expression     # Binary
              | expression op=('+'|'-') expression         # Binary
              | expression op=('<<'|'>>'|'>>>') expression # Binary
              | expression op='&' expression               # Binary
              | expression op='^' expression               # Binary
              | expression op='|' expression               # Binary
              | atom                                       # Element
              ;

atom          : value                                      # Variable
              | '(' expression ')'                         # Paren
              ;

// other
origin        : ORIGIN expression ;

align         : ALIGN expression ;

// function block
functionBlock : FUNCTION ID end
                functionLine*
                ENDFUNCTION end
              ;

functionLine  : codeLabel? (inst | dataDecl | align)? end
              | dataBlock
              | constBlock
              ;

// const block
constBlock    : CONST constDecl end
              | CONST end
                constLine*
                ENDCONST end
              ; 

constLine     : constDecl? end ;

constDecl     : ID '=' expression ;

// data block
dataBlock     : DATA dataDecl end
              | DATA end
                dataLine*
                ENDDATA end
              ;

dataLine      : (dataDecl | align)? end ;

dataDecl      : DATATYPE ID? (dataArray | dataEntries) ;
              
dataEntries   : '=' dataValue (',' dataValue)* ;

dataArray     : '[' expression ']' '=' expression ;

dataValue     : expression
              | STRING
              ;

// instructions
inst          : OR0                                                     # TypeR0
              | OSR1 SYSREG ',' REG                                     # TypeSR1
              | ORS1 REG ',' SYSREG                                     # TypeRS1
              | OI1  expression                                         # TypeI1
              | IR   '[' REG ']'                                        # TypeIR
              | RI2  REG ',' REG                                        # TypeR2A
              | RI2  REG ',' expression                                 # TypeI2
              | OR2  REG ',' REG                                        # TypeR2B
              | RI3  REG ',' REG ',' REG                                # TypeR3
              | RI3  REG ',' REG ',' expression                         # TypeI3A
              | OI3  REG ',' REG ',' expression                         # TypeI3B
              | OR4  REG ',' REG ',' REG ',' REG                        # TypeR4
              | BR   '[' REG ']' (',' COND)?                            # TypeBRRGI
              | BR   '[' REG imm ']' (',' COND)?                        # TypeBRRGO
              | BR   '[' expression ']' (',' COND)?                     # TypeBRPCR
              | LD   REG ',' '[' REG ']' ',' MTYPE                      # TypeLDRGI
              | LD   REG ',' '[' REG imm ']' ',' MTYPE (',' INCTYPE)?   # TypeLDRGO
              | LD   REG ',' '[' expression ']' ',' MTYPE               # TypeLDPCR
              | ST   '[' REG ']' ',' REG ',' MTYPE                      # TypeSTRGI
              | ST   '[' REG imm ']' ',' REG ',' MTYPE (',' INCTYPE)?   # TypeSTRGO
              | ST   '[' expression ']' ',' REG ',' MTYPE               # TypeSTPCR
              ;

// immediates
imm           : (PLUS | MINUS) expression ;

/*------------------------------- Tokens -------------------------------------*/

// new features

ALIGN       : 'align' ;

//AUTOALIGN   : 'autoalign' ;

//ARRAY       : 'array' ;

ORIGIN      : 'origin' ;

FUNCTION    : 'function' ;

ENDFUNCTION : 'endfunction' ;

CONST       : 'const' ;

ENDCONST    : 'endconst' ;

DATA        : 'data' ;

ENDDATA     : 'enddata' ;

DATATYPE    : 'bytes' | 'words' | 'dwords' ;

// old features

OR0         : 'break' | 'nop' ; // only zero registers

OSR1        : 'ssr' ;           // only 1 system register and 1 register

ORS1        : 'lsr' ;           // only 1 register and 1 system register
    
OI1         : 'int' ;           // only 1 immediate

IR          : 'iret' ;          // only 1 register surrounded by brackets

RI2         : 'cmp'             // 1 register and another register or an immediate
            | 'teq'
            | 'tst'
            | 'ucmp'
            | 'mov'
            ;

OR2         : 'not' ;           // only 2 registers

RI3         : 'adc'             // 2 registers and another register or an immediate
            | 'add'
            | 'and'
            | 'or'
            | 'sbb'
            | 'sub'
            | 'uadc'
            | 'uadd'
            | 'usbb'
            | 'usub'
            | 'xor'
            | 'rcl'
            | 'rcr'
            | 'rol'
            | 'ror'
            | 'sar'
            | 'shl'
            | 'shr'
            ;
            
OI3         : 'mui' ;           // only 2 registers and an immediate
            
OR4         : 'sdiv'            // only 4 registers
            | 'smul'
            | 'udiv'
            | 'umul'
            ;

BR          : 'br'
            | 'brl'
            ;

LD          : 'ld'
            ;

ST          : 'st'
            ;

REG         : 'r0'  | 'v0'  |
              'r1'  | 'v1'  |
              'r2'  | 'a0'  |
              'r3'  | 'a1'  |
              'r4'  | 'a2'  |
              'r5'  | 'a3'  |
              'r6'  | 'a4'  |
              'r7'  | 'a5'  |
              'r8'  | 'a6'  |
              'r9'  | 'a7'  |
              'r10' | 't0'  |
              'r11' | 't1'  |
              'r12' | 't2'  |
              'r13' | 't3'  |
              'r14' | 't4'  |
              'r15' | 't5'  |
              'r16' | 't6'  |
              'r17' | 't7'  |
              'r18' | 't8'  |
              'r19' | 't9'  |
              'r20' | 't10' |
              'r21' | 't11' |
              'r22' | 't12' |
              'r23' | 't13' |
              'r24' | 't14' |
              'r25' | 't15' |
              'r26' | 't16' |
              'r27' | 't17' |
              'r28' | 'fp'  |
              'r29' | 'ea'  |
              'r30' | 'ra'  |
              'r31' | 'sp'
            ;

SYSREG      : 'sys0'  | 'sys1'  | 'sys2'  | 'sys3'  | 'sys4'  | 'sys5'  | 'sys6'  | 'sys7'  |
              'sys8'  | 'sys9'  | 'sys10' | 'sys11' | 'sys12' | 'sys13' | 'sys14' | 'sys15' |
              'sys16' | 'sys17' | 'sys18' | 'sys19' | 'sys20' | 'sys21' | 'sys22' | 'sys23' |
              'sys24' | 'sys25' | 'sys26' | 'sys27' | 'sys28' | 'sys29' | 'sys30' | 'sys31' ;

MTYPE       : 'byte'  | 'ubyte' | 'sbyte' | 'word' | 'uword' | 'sword' | 'dword'  ;

INCTYPE     : 'pre' | 'post' ;

COND        : 'unc' | 'zr'  | '=='  | '!zr' | '!='  | 'cr'  | '>=u' | '!cr'
            | '<u'  | 'ov'  | '!ov' | 'ng'  | '!ng' | '>u'  | '<=u' | '>'
            | '<'   | '>='  | '<='  ;

LABEL       : ID ':' ;

ID          : [a-zA-Z_]+[a-zA-Z0-9_]* ;

DNUM        : [0-9]+ ;

HNUM        : [0-9]+[0-9a-fA-F]*'h' ; // we can have actions in the lexer if needed
            
BNUM        : [0-1]+'b' ;

PLUS        : '+' ;

MINUS       : '-' ;

NOT         : '~' ;

MULTIPLY    : '*' ;

DIVIDE      : '/' ;

MODULUS     : '%' ;

SHIFTLEFT   : '<<' ;

SHIFTRIGHT  : '>>' ;

SHIFTRIGHTARITH : '>>>' ;

AND         : '&' ;

XOR         : '^' ;

OR          : '|' ;

COMMA       : ',' ;

LBRACKET    : '[' ;

RBRACKET    : ']' ;

EQUALS      : '=' ;

LPAREN      : '(' ;

RPAREN      : ')' ;

NEWLINE     : '\r'? '\n' ; // match newline

CHAR        : '\'' . '\'' ;

STRING      : '\"' ~[\"]+ '\"' ;

SINGLE_CMNT : '//' ~[\n]*  -> skip;  // ignore comments in lexer

//MULTI_CMNT  : '/*' ~['*/']* '*/' -> skip;  // ignore comments in lexer

COMMENT     : '/*' (COMMENT|.)*? '*/' -> skip ;

//LINE_COMMENT: '//' .*? '\n' -> skip ;

WHITESPACE  : [ \t]+       -> channel(HIDDEN); // ignore whitespace in lexer

FILEEND     : EOF ;

//OTHER       : . ; // anything else


//mode MacroBody ;

//WHITESPACE  : [ \t]+ ; // don't ignore whitespace in lexer

