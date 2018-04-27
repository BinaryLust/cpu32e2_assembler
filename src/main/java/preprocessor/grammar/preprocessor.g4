/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

grammar preprocessor;

// parser stuff

input       : (macroBlock | masterLine)+ ;

masterLine  : LABEL? (macroUse | ignore) NEWLINE ;

macroBlock  : MACRO ID param? NEWLINE
              macroLine+
              ENDMACRO NEWLINE
            ;

param       : '(' ID (',' ID)* ')' ;

paramValue  : ~(',' | NEWLINE)+ ;

macroLine   : LABEL? (macroUse | ignore)? NEWLINE ;

macroUse    : ID paramValue? (',' paramValue)* ; // this should be macro functions only

ignore      : ~(NEWLINE | ENDMACRO)+ ;

// lexer tokens

MACRO       : 'macro' ;//     -> pushMode(MacroBody) ;

ENDMACRO    : 'endmacro' ; // -> popMode ;

LPAREN      : '(' ;

RPAREN      : ')' ;

COMMA       : ',' ;

LABEL       : ID ':' ;

ID          : [a-zA-Z_]+[a-zA-Z0-9_]* ;

NEWLINE     : '\r'? '\n' ; // match newline

WHITESPACE  : [ \t]+       -> channel(HIDDEN); // ignore whitespace in lexer

FILEEND     : EOF ;

