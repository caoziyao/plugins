// 表明SearchLexer.g4文件是词法分析器(lexer)定义文件
// 词法分析器的名称一定要和文件名保持一致
grammar Gua;

//一种action,定义生成的词法语法解析文件的头，当使用java的时候，生成的类需要包名，可以在这里统一定义
@header {
 package com.zel.g4;
}


prog
    : stat+
    ;

stat
    : expr                   # printExpr
    | ID '=' expr            # assign
    ;

expr
    : expr op=(MUL|DIV) expr # MulDiv
    | expr op=(ADD|SUB) expr # AddSub
    | INT                    # int
    | ID                     # id
    | '(' expr ')'           # parens
    ;

MUL : '*' ;

DIV : '/' ;

ADD : '+' ;

SUB : '-' ;

ID  : [a-zA-Z]+ ;

INT : [0-9]+ ;

WS  : [ \t\r\n]+ -> skip ;    // toss out whitespace